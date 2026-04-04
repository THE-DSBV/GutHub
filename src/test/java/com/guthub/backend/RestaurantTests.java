package com.guthub.backend;
import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.model.RestaurantReview;
import com.guthub.backend.repository.RestaurantRepository;

import jakarta.persistence.OneToMany;

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
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestaurantTests {
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getRestaurant() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/restaurants/1")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(1L, receivedJson.get("id").longValue());
        assertEquals("Riz Gluten-Free Asian Kitchen", receivedJson.get("name").textValue());
        assertEquals("3471 Yonge St, North York, Toronto, ON", receivedJson.get("location").textValue());
        assertEquals("Asian", receivedJson.get("cuisineType").textValue());
        assertEquals(true, receivedJson.get("glutenFree").booleanValue());
        assertEquals(true, receivedJson.get("featured").booleanValue());
        assertEquals(true, receivedJson.get("celiacCertified").booleanValue());
    }
    
    @Test
    void addRestaurant() throws Exception{
        ObjectNode recipeJson = objectMapper.createObjectNode();
        recipeJson.put("name", "Added Restaurant Test");
        recipeJson.put("location", "Added Test restaurant location");
        recipeJson.put("cuisineType", "Added Test restaurant cuisine type");
        recipeJson.put("glutenFree", true);
        recipeJson.put("featured", true);
        recipeJson.put("celiacCertified", false);
        MockHttpServletResponse response = mockMvc.perform(post("/restaurants").contentType("application/json").content(recipeJson.toString())).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        List<Restaurant> restaurants = restaurantRepository.findAll();
        assertTrue(!restaurants.isEmpty());
        Restaurant addedRestaurant = restaurants.get(restaurants.size() - 1);
        assertEquals("Added Restaurant Test", addedRestaurant.getName());
        assertEquals("Added Test restaurant location", addedRestaurant.getLocation());
        assertEquals("Added Test restaurant cuisine type", addedRestaurant.getCuisineType());
        assertEquals(true, addedRestaurant.isGlutenFree());
    }

    @Test
    void deleteRestaurant() throws Exception{
        Restaurant r = new Restaurant();
        r.setId(123456L);
        r.setName("Delete Restaurant Test");
        r.setLocation("test location");
        r.setCuisineType("test cuisine type");
        r.setGlutenFree(false);
        restaurantRepository.save(r);
        MockHttpServletResponse response = mockMvc.perform(delete("/restaurants/123456").contentType("application/json")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertTrue(restaurantRepository.findById(123456L).isEmpty());
    }

    @Test
    void getAllRestaurants() throws Exception {
        MockHttpServletResponse response = mockMvc
            .perform(get("/restaurants"))
            .andReturn()
            .getResponse();
        assertEquals(200, response.getStatus());
        String content = response.getContentAsString();
        assertTrue(content.startsWith("["));
        assertTrue(content.length() > 2);
    }

    @Test
    void filterRestaurants() throws Exception {
        Restaurant r = new Restaurant();
        r.setName("Italian Test");
        r.setLocation("Italy");
        r.setCuisineType("Italian");
        r.setGlutenFree(true);
        r.setFeatured(false);
        r.setCeliacCertified(false);
        restaurantRepository.save(r);
        MockHttpServletResponse response = mockMvc.perform(
            get("/restaurants")
                .param("cuisine", "Italian")
                .param("glutenFree", "true")
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String content = response.getContentAsString();
        assertTrue(content.contains("Italian Test"));
    }

    @Test
    void searchRestaurants() throws Exception {
        Restaurant r = new Restaurant();
        r.setName("Sushi Place");
        r.setLocation("Japan");
        r.setCuisineType("Japanese");
        r.setGlutenFree(true);
        r.setFeatured(false);
        r.setCeliacCertified(false);
        restaurantRepository.save(r);
        MockHttpServletResponse response = mockMvc.perform(
            get("/restaurants/search")
                .param("keyword", "Sushi")
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String content = response.getContentAsString();
        assertTrue(content.contains("Sushi Place"));
    }

    @Test
    void updateRestaurant() throws Exception {
        Restaurant r = new Restaurant();
        r.setName("Old Name");
        r.setLocation("Old Location");
        r.setCuisineType("Old Cuisine");
        r.setGlutenFree(false);
        r.setFeatured(false);
        r.setCeliacCertified(false);
        restaurantRepository.save(r);

        ObjectNode updatedJson = objectMapper.createObjectNode();
        updatedJson.put("name", "Updated Name");
        updatedJson.put("location", "Updated Location");
        updatedJson.put("cuisineType", "Updated Cuisine");
        updatedJson.put("glutenFree", true);
        updatedJson.put("featured", true);
        updatedJson.put("celiacCertified", true);

        MockHttpServletResponse response = mockMvc.perform(
            put("/restaurants/" + r.getId())
                .contentType("application/json")
                .content(updatedJson.toString())
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Restaurant updated = restaurantRepository.findById(r.getId()).get();
        assertEquals("Updated Name", updated.getName());
        assertEquals(true, updated.isGlutenFree());
    }


}
