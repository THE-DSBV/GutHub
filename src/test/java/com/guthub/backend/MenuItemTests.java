package com.guthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.guthub.backend.model.MenuItem;
import com.guthub.backend.model.Restaurant;

import com.guthub.backend.repository.MenuItemRepository;
import com.guthub.backend.repository.RestaurantRepository;

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
public class MenuItemTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // ───────────── GET ALL ─────────────

    @Test
    void getAllMenuItems() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/menu-items")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().startsWith("["));
    }

    // ───────────── GET BY ID ─────────────

    @Test
    void getMenuItemById() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test R");
        restaurant.setLocation("Loc");
        restaurant.setCuisineType("Type");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        MenuItem item = new MenuItem();
        item.setItemName("Burger");
        item.setDescription("Nice burger");
        item.setCeliacCertified(true);
        item.setRestaurant(restaurant);
        menuItemRepository.save(item);

        MockHttpServletResponse response = mockMvc.perform(
                get("/menu-items/" + item.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        String content = response.getContentAsString();
        assertTrue(content.contains("Burger"));
    }

    // ───────────── CREATE ─────────────

    @Test
    void createMenuItem() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Create R");
        restaurant.setLocation("Loc");
        restaurant.setCuisineType("Type");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("itemName", "Pizza");
        json.put("description", "Cheesy");
        json.put("celiacCertified", true);

        ObjectNode restNode = objectMapper.createObjectNode();
        restNode.put("id", restaurant.getId());
        json.set("restaurant", restNode);

        MockHttpServletResponse response = mockMvc.perform(
                post("/menu-items/restaurant/" + restaurant.getId())
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        List<MenuItem> items = menuItemRepository.findAll();
        assertTrue(!items.isEmpty());

        MenuItem added = items.get(items.size() - 1);
        assertEquals("Pizza", added.getItemName());
        assertEquals("Cheesy", added.getDescription());
    }

    // ───────────── UPDATE ─────────────

    @Test
    void updateMenuItem() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Update R");
        restaurant.setLocation("Loc");
        restaurant.setCuisineType("Type");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        MenuItem item = new MenuItem();
        item.setItemName("Old Item");
        item.setDescription("Old desc");
        item.setCeliacCertified(false);
        item.setRestaurant(restaurant);
        menuItemRepository.save(item);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("itemName", "Updated Item");
        json.put("description", "Updated desc");
        json.put("celiacCertified", true);

        ObjectNode restNode = objectMapper.createObjectNode();
        restNode.put("id", restaurant.getId());
        json.set("restaurant", restNode);

        MockHttpServletResponse response = mockMvc.perform(
                put("/menu-items/" + item.getId())
                        .contentType("application/json")
                        .content(json.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        MenuItem updated = menuItemRepository.findById(item.getId()).get();
        assertEquals("Updated Item", updated.getItemName());
        assertEquals("Updated desc", updated.getDescription());
        assertTrue(updated.isCeliacCertified());
    }

    // ───────────── DELETE ─────────────

    @Test
    void deleteMenuItem() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Delete R");
        restaurant.setLocation("Loc");
        restaurant.setCuisineType("Type");
        restaurant.setGlutenFree(true);
        restaurantRepository.save(restaurant);

        MenuItem item = new MenuItem();
        item.setItemName("To Delete");
        item.setDescription("desc");
        item.setCeliacCertified(false);
        item.setRestaurant(restaurant);
        menuItemRepository.save(item);

        MockHttpServletResponse response = mockMvc.perform(
                delete("/menu-items/" + item.getId())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(menuItemRepository.findById(item.getId()).isEmpty());
    }
}