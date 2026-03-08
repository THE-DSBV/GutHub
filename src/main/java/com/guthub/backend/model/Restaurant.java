package com.guthub.backend.model;
import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String cuisineType;
    private boolean glutenFree;
    private boolean featured;
    private boolean celiacCertified;



    // Constructors (optional)
    public Restaurant() {}

    public Restaurant(String name, String location, String cuisineType, boolean glutenFree, boolean featured, boolean celiacCertified) {
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

    public boolean isGlutenFree() { //Gluten
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isFeatured() { // Featured
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isCeliacCertified() { // Celiac
        return celiacCertified;
    }

    public void setCeliacCertified(boolean celiacCertified) {
        this.celiacCertified = celiacCertified;
    }


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews;
    
}
