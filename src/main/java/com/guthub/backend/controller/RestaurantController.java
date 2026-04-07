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

import com.guthub.backend.controller.dto.MenuItemDTO;
import com.guthub.backend.model.MenuItem;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.repository.RestaurantRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Boolean glutenFree,
            @RequestParam(required = false) Boolean celiacCertified) {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        if (cuisine != null) {
            restaurants = restaurants.stream()
                    .filter(r -> r.getCuisineType().equalsIgnoreCase(cuisine))
                    .toList();
        }

        if (minRating != null) {
            restaurants = restaurants.stream()
                    .filter(r -> r.getAverageRating() >= minRating)
                    .toList();
        }

        if (glutenFree != null) {
        restaurants = restaurants.stream()
                .filter(r -> r.isGlutenFree() != null && r.isGlutenFree().equals(glutenFree))
                .toList();
        }

        if (celiacCertified != null) {
            restaurants = restaurants.stream()
                    .filter(r -> r.isCeliacCertified() != null && r.isCeliacCertified().equals(celiacCertified))
                    .toList();
        }
        
        restaurants.forEach(r -> {
            if (r.getMenuItems() != null) {
                r.setMenuItems(r.getMenuItems().stream()
                    .map(m -> {
                        MenuItemDTO dto = mapToDTO(m);

                        MenuItem mi = new MenuItem();
                        mi.setId(dto.getId());
                        mi.setItemName(dto.getItemName());
                        mi.setDescription(dto.getDescription());
                        mi.setCeliacCertified(dto.getCeliacCertified());

                        return mi;
                    }).toList());
            }
        });

        return restaurants;
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        restaurant.ifPresent(r -> {
            if (r.getMenuItems() != null) {
                r.setMenuItems(r.getMenuItems().stream()
                    .map(m -> {
                        MenuItemDTO dto = mapToDTO(m);
                        MenuItem mi = new MenuItem();
                        mi.setId(dto.getId());
                        mi.setItemName(dto.getItemName());
                        mi.setDescription(dto.getDescription());
                        mi.setCeliacCertified(dto.getCeliacCertified());
                        return mi;
                    }).toList());
            }
        });
        return restaurant;
    }

    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @Valid @RequestBody Restaurant updatedRestaurant) {
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

    /* 
    //moved to main get mapping
    @GetMapping("/moreThan/{minRating}")
    public List<Restaurant> getTopRated(@PathVariable Double minRating) {
        return restaurantRepository.findByMinAverageRating(minRating);
    }
    */

   private MenuItemDTO mapToDTO(MenuItem menuItem) {
    return new MenuItemDTO(
        menuItem.getId(),
        menuItem.getItemName(),
        menuItem.getDescription(),
        menuItem.isCeliacCertified()
    );  
    }

    @GetMapping("/{id}/menuItems")
public List<MenuItem> getMenuItemsByRestaurant(@PathVariable Long id) {
    Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    return restaurant.getMenuItems();
}
    
}
