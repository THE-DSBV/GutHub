package com.guthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.User;

import com.guthub.backend.repository.RecipeRepository;
import com.guthub.backend.repository.RestaurantRepository;
import com.guthub.backend.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void registerUser() throws Exception {
        ObjectNode json = objectMapper.createObjectNode();
        json.put("username", "user_test1");
        json.put("password", "pass");

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/register")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(userRepository.findByUsername("user_test1").isPresent());
    }

    @Test
    void registerDuplicateUser() throws Exception {
        User u = new User("dupUser", "pass");
        userRepository.save(u);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("username", "dupUser");
        json.put("password", "pass");

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/register")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void loginSuccess() throws Exception {
        User u = new User("loginUser", "pass");
        userRepository.save(u);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("username", "loginUser");
        json.put("password", "pass");

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/login")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("success"));
    }

    @Test
    void loginWrongPassword() throws Exception {
        User u = new User("loginUser2", "correct");
        userRepository.save(u);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("username", "loginUser2");
        json.put("password", "wrong");

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/login")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(401, response.getStatus());
    }

    @Test
    void addAndGetFavouriteRecipe() throws Exception {
        User user = new User("favUser", "pass");
        user.setFavouriteRecipes(new ArrayList<>());
        userRepository.save(user);

        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setIngredients("ing");
        recipe.setInstructions("inst");
        recipe.setGlutenFree(true);
        recipeRepository.save(recipe);
        MockHttpServletResponse addResponse = mockMvc.perform(
                post("/users/favUser/favourites/recipes/" + recipe.getId())
        ).andReturn().getResponse();

        assertEquals(200, addResponse.getStatus());

        MockHttpServletResponse getResponse = mockMvc.perform(
                get("/users/favUser/favourites/recipes")
        ).andReturn().getResponse();

        assertEquals(200, getResponse.getStatus());
        assertTrue(getResponse.getContentAsString().contains("Test Recipe"));
    }

    @Test
    void addDuplicateFavouriteRecipe() throws Exception {
        User user = new User("dupFavUser", "pass");
        user.setFavouriteRecipes(new ArrayList<>());
        userRepository.save(user);

        Recipe recipe = new Recipe();
        recipe.setName("Dup Recipe");
        recipe.setIngredients("ing");
        recipe.setInstructions("inst");
        recipe.setGlutenFree(true);
        recipeRepository.save(recipe);

        mockMvc.perform(post("/users/dupFavUser/favourites/recipes/" + recipe.getId()));

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/dupFavUser/favourites/recipes/" + recipe.getId())
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void removeFavouriteRecipe() throws Exception {
        User user = new User("removeUser", "pass");
        user.setFavouriteRecipes(new ArrayList<>());
        userRepository.save(user);

        Recipe recipe = new Recipe();
        recipe.setName("Remove Recipe");
        recipe.setIngredients("ing");
        recipe.setInstructions("inst");
        recipe.setGlutenFree(true);
        recipeRepository.save(recipe);

        mockMvc.perform(post("/users/removeUser/favourites/recipes/" + recipe.getId()));

        MockHttpServletResponse response = mockMvc.perform(
                delete("/users/removeUser/favourites/recipes/" + recipe.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void addAndGetFavouriteRestaurant() throws Exception {
        User user = new User("restUser", "pass");
        user.setFavouriteRestaurants(new ArrayList<>());
        userRepository.save(user);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setLocation("Test");
        restaurant.setCuisineType("Test");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        mockMvc.perform(
                post("/users/restUser/favourites/restaurants/" + restaurant.getId())
        );

        MockHttpServletResponse response = mockMvc.perform(
                get("/users/restUser/favourites/restaurants")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("Test Restaurant"));
    }

    @Test
    void addDuplicateFavouriteRestaurant() throws Exception {
        User user = new User("dupRestUser", "pass");
        user.setFavouriteRestaurants(new ArrayList<>());
        userRepository.save(user);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Dup Restaurant");
        restaurant.setLocation("Test");
        restaurant.setCuisineType("Test");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        mockMvc.perform(post("/users/dupRestUser/favourites/restaurants/" + restaurant.getId()));

        MockHttpServletResponse response = mockMvc.perform(
                post("/users/dupRestUser/favourites/restaurants/" + restaurant.getId())
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void removeFavouriteRestaurant() throws Exception {
        User user = new User("removeRestUser", "pass");
        user.setFavouriteRestaurants(new ArrayList<>());
        userRepository.save(user);
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Remove Restaurant");
        restaurant.setLocation("Test");
        restaurant.setCuisineType("Test");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        mockMvc.perform(post("/users/removeRestUser/favourites/restaurants/" + restaurant.getId()));

        MockHttpServletResponse response = mockMvc.perform(
                delete("/users/removeRestUser/favourites/restaurants/" + restaurant.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}