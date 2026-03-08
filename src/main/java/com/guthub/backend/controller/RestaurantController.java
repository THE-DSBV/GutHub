package com.guthub.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
