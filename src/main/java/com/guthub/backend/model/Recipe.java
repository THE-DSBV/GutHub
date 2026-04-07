package com.guthub.backend.model;

import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Ingredients are required")
    private String ingredients;
    @NotBlank(message = "Instructions are required")
    private String instructions;
    private Boolean glutenFree;
    private Boolean featured;
    private String createdBy;
    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeReview> reviews;
    @Transient
    private Double averageRating;
    @Nullable
    private String imageUrl;


    public Recipe() {}

    public Recipe(String name, String ingredients, String instructions, Boolean glutenFree, Boolean featured) {
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

    public Boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
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
        double avg = sum / reviews.size();
        return Math.round(avg * 10.0) / 10.0; 
    }

    public Boolean isFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}