package com.guthub.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.Restaurant;
import com.guthub.backend.repository.RestaurantRepository;

// Available API endpoints:
// GET /recipes
// GET /recipes/{id}
// POST /recipes
// PUT /recipes/{id}
// DELETE /recipes/{id}

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id).map(restaurant -> {
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setLocation(updatedRestaurant.getLocation());
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
            restaurant.setGlutenFree(updatedRestaurant.isGlutenFree());
            restaurant.setFeatured(updatedRestaurant.isFeatured());
            restaurant.setCeliacCertified(updatedRestaurant.isCeliacCertified());
            return restaurantRepository.save(restaurant);
        }).orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
    }

    //multi keyword search
    @GetMapping("/search")
    public List<Restaurant> searchRestaurants(@RequestParam String keyword) {
        String[] words = keyword.split(" ");
        List<Restaurant> results = restaurantRepository.findAll();
        return results.stream().filter(restaurant -> {
            String name = restaurant.getName().toLowerCase();
            for (String word : words) {
                if (name.contains(word.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }).toList();
    }

    @GetMapping("/moreThan/{minRating}")
    public List<Restaurant> getTopRated(@PathVariable Integer minRating) {
        return restaurantRepository.findByRatingGreaterThanEqual(minRating);
    }
    
}
