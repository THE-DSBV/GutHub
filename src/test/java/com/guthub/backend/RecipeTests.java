package com.guthub.backend;
import com.guthub.backend.controller.RecipeNotFoundException;
import com.guthub.backend.model.Recipe;
import com.guthub.backend.model.RecipeReview;
import com.guthub.backend.repository.RecipeRepository;

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
class RecipeTests {
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RecipeRepository recipeRepository;


    @Test
    void getRecipe() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/recipes/1")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(1L, receivedJson.get("id").longValue());
        assertEquals("Avocado Egg Breakfast Bowl", receivedJson.get("name").textValue());
        assertEquals("eggs, avocado, spinach, salt", receivedJson.get("ingredients").textValue());
        assertEquals("Cook eggs and assemble bowl.", receivedJson.get("instructions").textValue());
        assertEquals(true, receivedJson.get("glutenFree").booleanValue());
    }
    
    @Test
    void addRecipe() throws Exception{
        ObjectNode recipeJson = objectMapper.createObjectNode();
        recipeJson.put("name", "Added Recipe Test");
        recipeJson.put("ingredients", "Added Test recipe ingredients");
        recipeJson.put("instructions", "Added Test recipe instructions");
        recipeJson.put("glutenFree", true);
        MockHttpServletResponse response = mockMvc.perform(post("/recipes").contentType("application/json").content(recipeJson.toString())).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        List<Recipe> recipes = recipeRepository.findAll();
        assertTrue(!recipes.isEmpty());
        Recipe addedRecipe = recipes.get(recipes.size() - 1);
        assertEquals("Added Recipe Test", addedRecipe.getName());
        assertEquals("Added Test recipe ingredients", addedRecipe.getIngredients());
        assertEquals("Added Test recipe instructions", addedRecipe.getInstructions());
        assertEquals(true, addedRecipe.isGlutenFree());
    }

    @Test
    void deleteRecipe() throws Exception{
        Recipe r = new Recipe();
        r.setId(123456L);
        r.setName("Delete Recipe Test");
        r.setIngredients("test ingredients");
        r.setInstructions("test instructions");
        r.setGlutenFree(false);
        recipeRepository.save(r);
        MockHttpServletResponse response = mockMvc.perform(delete("/recipes/123456").contentType("application/json")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertTrue(recipeRepository.findById(123456L).isEmpty());
    }
}
