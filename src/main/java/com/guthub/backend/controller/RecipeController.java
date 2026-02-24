package com.guthub.backend.controller;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
