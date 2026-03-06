package com.guthub.backend.service;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            return recipeRepository.save(recipe);
        }).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}