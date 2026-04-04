package com.guthub.backend;

import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.RecipeReview;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.RestaurantReview;
import com.guthub.backend.model.User;
import com.guthub.backend.repository.RecipeRepository;
import com.guthub.backend.repository.RecipeReviewRepository;
import com.guthub.backend.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeReviewTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RecipeReviewRepository reviewRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    // helper method to create valid user
    private User createUser() {
        User user = new User();
        user.setUsername("testuser" + System.currentTimeMillis());
        user.setPassword("password");
        return userRepository.save(user);
    }

    // helper method to get recipe
    private Recipe getRecipe() {
        return recipeRepository.findById(1L).orElseThrow();
    }

    @Test
    void getAllReviews() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/recipe-reviews"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void getReviewById() throws Exception {
        User user = createUser();
        Recipe recipe = getRecipe();

        RecipeReview review = new RecipeReview(8, "Solid food", recipe);
        review.setUser(user);
        reviewRepository.save(review);

        MockHttpServletResponse response = mockMvc
                .perform(get("/recipe-reviews/" + review.getId()))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void updateReview() throws Exception {
        User user = createUser();
        Recipe recipe = getRecipe();

        RecipeReview review = new RecipeReview(5, "Old review", recipe);
        review.setUser(user);
        reviewRepository.save(review);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("rating", 10);
        json.put("text", "Updated review!");

        ObjectNode recipeNode = objectMapper.createObjectNode();
        recipeNode.put("id", 1);
        json.set("recipe", recipeNode);

        ObjectNode userNode = objectMapper.createObjectNode();
        userNode.put("username", user.getUsername());
        json.set("user", userNode);

        MockHttpServletResponse response = mockMvc.perform(
                put("/recipe-reviews/" + review.getId())
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteReview() throws Exception {
        User user = createUser();
        Recipe recipe = getRecipe();

        RecipeReview review = new RecipeReview(6, "To delete", recipe);
        review.setUser(user);
        reviewRepository.save(review);

        MockHttpServletResponse response = mockMvc.perform(
                delete("/recipe-reviews/" + review.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(reviewRepository.findById(review.getId()).isEmpty());
    }

    @Test
    void addReviewToRecipe() throws Exception {
        User user = new User();
        user.setUsername("testuser_add");
        user.setPassword("password");
        userRepository.save(user);
        Recipe recipe = recipeRepository.findById(1L).orElseThrow();
        ObjectNode json = objectMapper.createObjectNode();
        json.put("username", "testuser_add");
        json.put("rating", 8);
        json.put("text", "Good.");
        MockHttpServletResponse response = mockMvc.perform(
                post("/recipe-reviews/recipe/1")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        List<RecipeReview> reviews = reviewRepository.findAll();
        assertTrue(!reviews.isEmpty());
        RecipeReview added = reviews.get(reviews.size() - 1);
        assertEquals(8, added.getRating());
        assertEquals("Good.", added.getText());
        assertEquals("testuser_add", added.getUser().getUsername());
        assertEquals(1L, added.getRecipe().getId());
    }
}