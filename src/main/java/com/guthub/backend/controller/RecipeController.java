package com.guthub.backend.controller;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Available API endpoints:
// GET /recipes
// GET /recipes/{id}
// POST /recipes
// PUT /recipes/{id}
// DELETE /recipes/{id}

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            recipe.setGlutenFree(updatedRecipe.isGlutenFree());
            return recipeRepository.save(recipe);
        }).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
    }
}