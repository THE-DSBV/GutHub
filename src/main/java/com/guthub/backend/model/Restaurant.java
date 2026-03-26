package com.guthub.backend.model;
import jakarta.persistence.*;
import java.util.List;

import io.micrometer.common.lang.Nullable;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String cuisineType;
    private Boolean glutenFree;
    private Boolean featured;
    private Boolean celiacCertified;
    private String createdBy;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantReview> reviews;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
    @Transient
    private Double averageRating;
    @Nullable
    private String imageUrl;



    // Constructors (optional)
    public Restaurant() {}

    public Restaurant(String name, String location, String cuisineType, Boolean glutenFree, Boolean featured, Boolean celiacCertified) {
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.glutenFree = glutenFree;
        this.featured = featured;
        this.celiacCertified = celiacCertified;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { //Name 
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType() { //Cuisine 
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
    public String getLocation() { //Location
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isGlutenFree() { //Gluten
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean isFeatured() { // Featured
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean isCeliacCertified() { // Celiac
        return celiacCertified;
    }

    public void setCeliacCertified(Boolean celiacCertified) {
        this.celiacCertified = celiacCertified;
    }
    
    public List<RestaurantReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RestaurantReview> reviews) {
        this.reviews = reviews;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (RestaurantReview review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
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
