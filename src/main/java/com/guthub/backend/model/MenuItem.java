package com.guthub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private Boolean celiacCertified;

    @ManyToOne

    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public MenuItem() {}

    public MenuItem(String itemName, String description, boolean celiacCertified, Restaurant restaurant) {
        this.itemName = itemName;
        this.description = description;
        this.celiacCertified = celiacCertified;
        this.restaurant = restaurant;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCeliacCertified() { return celiacCertified; }
    public void setCeliacCertified(boolean celiacCertified) { this.celiacCertified = celiacCertified; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}