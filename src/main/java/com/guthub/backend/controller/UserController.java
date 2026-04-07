package com.guthub.backend.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.User;
import com.guthub.backend.repository.UserRepository;
import com.guthub.backend.repository.RecipeRepository;
import com.guthub.backend.repository.RestaurantRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final RestaurantRepository restaurantRepository;

    public UserController(UserRepository userRepository, RecipeRepository recipeRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String rawPassword = userData.get("password");

        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        if (rawPassword == null || rawPassword.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password is required"));
        }
        if (userRepository.findByUsername(username).isPresent()) {
            System.err.println("Error: Username '" + username + "' is already taken.");
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
        }

        User newUser = new User(username, rawPassword);
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        if (password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password is required"));
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of(
                "status", "error",
                "message", "Invalid username or password"
            ));
        }


        String sessionToken = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of(
            "username", username,
            "status", "success",
            "token", sessionToken,
            "isAdmin", user.getIsAdmin()
        ));
    }

    @GetMapping("/{username}/favourites/recipes")
    public ResponseEntity<?> getFavouriteRecipes(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getFavouriteRecipes());
    }

    @PostMapping("/{username}/favourites/recipes/{recipeId}")
    public ResponseEntity<?> addFavouriteRecipe(@PathVariable String username, @PathVariable Long recipeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (user.getFavouriteRecipes().contains(recipe)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Recipe already in favourites"));
        }

        user.getFavouriteRecipes().add(recipe);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Recipe added to favourites"));
    }

    @DeleteMapping("/{username}/favourites/recipes/{recipeId}")
    public ResponseEntity<?> removeFavouriteRecipe(@PathVariable String username, @PathVariable Long recipeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        user.getFavouriteRecipes().remove(recipe);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Recipe removed from favourites"));
    }

    // ── Favourite Restaurants ──────────────────────────────────────

    @GetMapping("/{username}/favourites/restaurants")
    public ResponseEntity<?> getFavouriteRestaurants(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user.getFavouriteRestaurants());
    }

    @PostMapping("/{username}/favourites/restaurants/{restaurantId}")
    public ResponseEntity<?> addFavouriteRestaurant(@PathVariable String username, @PathVariable Long restaurantId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (user.getFavouriteRestaurants().contains(restaurant)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Restaurant already in favourites"));
        }

        user.getFavouriteRestaurants().add(restaurant);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Restaurant added to favourites"));
    }

    @DeleteMapping("/{username}/favourites/restaurants/{restaurantId}")
    public ResponseEntity<?> removeFavouriteRestaurant(@PathVariable String username, @PathVariable Long restaurantId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        user.getFavouriteRestaurants().remove(restaurant);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Restaurant removed from favourites"));
    }
}