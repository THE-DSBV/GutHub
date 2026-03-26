package com.guthub.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import com.guthub.backend.model.User;
import com.guthub.backend.repository.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.RecipeReview;
import com.guthub.backend.repository.RecipeRepository;
import com.guthub.backend.repository.RecipeReviewRepository;

@RestController
@RequestMapping("/recipe-reviews")
public class RecipeReviewController {

    private final RecipeReviewRepository recipeReviewRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeReviewController(RecipeReviewRepository recipeReviewRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeReviewRepository = recipeReviewRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<RecipeReview> getAllRecipeReviews() {
        return recipeReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RecipeReview> getRecipeReviewById(@PathVariable Long id) {
        return recipeReviewRepository.findById(id);
    }

    @PostMapping
    public RecipeReview createRecipeReview(@RequestBody RecipeReview review) {
        // Ensure the recipe exists
        Recipe recipe = recipeRepository.findById(review.getRecipe().getId())
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + review.getRecipe().getId()));
        review.setRecipe(recipe);
        return recipeReviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public RecipeReview updateRecipeReview(@PathVariable Long id, @RequestBody RecipeReview updatedReview) {
        return recipeReviewRepository.findById(id).map(review -> {
            // Only update rating and text
            review.setRating(updatedReview.getRating());
            review.setText(updatedReview.getText());
            return recipeReviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeReview(@PathVariable Long id) {
        recipeReviewRepository.deleteById(id);
    }

    @PostMapping("/recipe/{recipeId}")
    public RecipeReview addReviewToRecipe(@PathVariable Long recipeId, @RequestBody Map<String, Object> body) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        String username = (String) body.get("username");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RecipeReview review = new RecipeReview();
        review.setRecipe(recipe);
        review.setUser(user);

        if (body.get("rating") != null) {
            review.setRating((int) body.get("rating"));
        }
        if (body.get("text") != null) {
            review.setText((String) body.get("text"));
        }

        return recipeReviewRepository.save(review);
    }
}