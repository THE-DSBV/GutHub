package com.guthub.backend.model;

import jakarta.persistence.*;
import java.util.List;

import io.micrometer.common.lang.Nullable;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ingredients;
    private String instructions; //Add this
    private boolean glutenFree;
    private boolean featured;
    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeReview> reviews;
    @Transient
    private Double averageRating;


    public Recipe() {}

    public Recipe(String name, String ingredients, String instructions, boolean glutenFree, boolean featured) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.glutenFree = glutenFree;
        this.featured = featured;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public List<RecipeReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RecipeReview> reviews) {
        this.reviews = reviews;
    }

    public Double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (RecipeReview review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public Boolean isFeatured() { // Featured
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
}