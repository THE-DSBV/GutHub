package com.guthub.backend.model;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this makes it so ids are automaticall generated, probably what we want
    private Long id;

    //random stuff idk what we asctually want
    private String name;
    private String ingredients;
    private boolean glutenFree;

    // getters & setters
}
