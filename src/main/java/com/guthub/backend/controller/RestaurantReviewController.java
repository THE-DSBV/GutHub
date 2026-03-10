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
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.RestaurantReview;
import com.guthub.backend.repository.RestaurantRepository;
import com.guthub.backend.repository.RestaurantReviewRepository;

@RestController
@RequestMapping("/restaurant-reviews")
public class RestaurantReviewController {

    private final RestaurantReviewRepository restaurantReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantReviewController(RestaurantReviewRepository restaurantReviewRepository, RestaurantRepository restaurantRepository) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<RestaurantReview> getAllRestaurantReviews() {
        return restaurantReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RestaurantReview> getRestaurantReviewById(@PathVariable Long id) {
        return restaurantReviewRepository.findById(id);
    }

    @PostMapping
    public RestaurantReview createRestaurantReview(@RequestBody RestaurantReview review) {
        // Ensure the restaurant exists
        Restaurant restaurant = restaurantRepository.findById(review.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + review.getRestaurant().getId()));
        review.setRestaurant(restaurant);
        return restaurantReviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public RestaurantReview updateRestaurantReview(@PathVariable Long id, @RequestBody RestaurantReview updatedReview) {
        return restaurantReviewRepository.findById(id).map(review -> {
            // Only update rating and text
            review.setRating(updatedReview.getRating());
            review.setText(updatedReview.getText());
            return restaurantReviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurantReview(@PathVariable Long id) {
        restaurantReviewRepository.deleteById(id);
    }
}