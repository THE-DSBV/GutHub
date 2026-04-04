/*package com.guthub.backend;

import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.RestaurantReview;
import com.guthub.backend.model.User;
import com.guthub.backend.repository.RestaurantRepository;
import com.guthub.backend.repository.RestaurantReviewRepository;
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
public class RestaurantReviewTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    // helper method to create valid user
    private User createUser() {
        User user = new User();
        user.setUsername("testuser" + System.currentTimeMillis());
        user.setPassword("password");
        return userRepository.save(user);
    }

    // helper method to get restaurant
    private Restaurant getRestaurant() {
        return restaurantRepository.findById(1L).orElseThrow();
    }

    @Test
    void getAllReviews() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/restaurant-reviews"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void getReviewById() throws Exception {
        User user = createUser();
        Restaurant restaurant = getRestaurant();

        RestaurantReview review = new RestaurantReview(8, "Solid food", restaurant);
        review.setUser(user);
        reviewRepository.save(review);

        MockHttpServletResponse response = mockMvc
                .perform(get("/restaurant-reviews/" + review.getId()))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void createReview() throws Exception {
        User user = createUser();

        ObjectNode json = objectMapper.createObjectNode();
        json.put("rating", 9);
        json.put("text", "Amazing experience!");

        ObjectNode restaurantNode = objectMapper.createObjectNode();
        restaurantNode.put("id", 1);
        json.set("restaurant", restaurantNode);

        ObjectNode userNode = objectMapper.createObjectNode();
        userNode.put("username", user.getUsername());
        json.set("user", userNode);

        MockHttpServletResponse response = mockMvc.perform(
                post("/restaurant-reviews")
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void updateReview() throws Exception {
        User user = createUser();
        Restaurant restaurant = getRestaurant();

        RestaurantReview review = new RestaurantReview(5, "Old review", restaurant);
        review.setUser(user);
        reviewRepository.save(review);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("rating", 10);
        json.put("text", "Updated review!");

        ObjectNode restaurantNode = objectMapper.createObjectNode();
        restaurantNode.put("id", 1);
        json.set("restaurant", restaurantNode);

        ObjectNode userNode = objectMapper.createObjectNode();
        userNode.put("username", user.getUsername());
        json.set("user", userNode);

        MockHttpServletResponse response = mockMvc.perform(
                put("/restaurant-reviews/" + review.getId())
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deleteReview() throws Exception {
        User user = createUser();
        Restaurant restaurant = getRestaurant();

        RestaurantReview review = new RestaurantReview(6, "To delete", restaurant);
        review.setUser(user);
        reviewRepository.save(review);

        MockHttpServletResponse response = mockMvc.perform(
                delete("/restaurant-reviews/" + review.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(reviewRepository.findById(review.getId()).isEmpty());
    }
*/