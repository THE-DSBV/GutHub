package com.guthub.backend.controller;

import java.util.List;
import java.util.Map;
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
import com.guthub.backend.model.User;
import com.guthub.backend.repository.RestaurantRepository;
import com.guthub.backend.repository.RestaurantReviewRepository;
import com.guthub.backend.repository.UserRepository;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/restaurant-reviews")
public class RestaurantReviewController {

    private final RestaurantReviewRepository restaurantReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantReviewController(RestaurantReviewRepository restaurantReviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<RestaurantReview> getAllRestaurantReviews() {
        return restaurantReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RestaurantReview> getRestaurantReviewById(@PathVariable Long id) {
        return restaurantReviewRepository.findById(id);
    }

    //This method is useless because addReviewToRestaurant is the proper way to create a review, but it's here for completeness sake
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

    @PostMapping("/restaurant/{restaurantId}")
    public RestaurantReview addReviewToRestaurant(@PathVariable Long restaurantId, @RequestBody Map<String, Object> body) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        String username = (String) body.get("username");
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Username is required");
        }
        Object ratingObj = body.get("rating");
        if (ratingObj == null) {
            throw new RuntimeException("Rating is required");
        }
        if (!(ratingObj instanceof Integer)) {
            throw new RuntimeException("Rating must be an integer");
        }
        int rating = (int) ratingObj;
        if (rating < 1 || rating > 10) {
            throw new RuntimeException("Rating must be between 1 and 10");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RestaurantReview review = new RestaurantReview();
        review.setRestaurant(restaurant);
        review.setUser(user);

        if (body.get("rating") != null) {
            review.setRating((int) body.get("rating"));
        }
        if (body.get("text") != null) {
            review.setText((String) body.get("text"));
        }

        return restaurantReviewRepository.save(review);
    }
}