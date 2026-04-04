package com.guthub.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.guthub.backend.model.MenuItem;
import com.guthub.backend.model.Restaurant;
import com.guthub.backend.repository.MenuItemRepository;
import com.guthub.backend.repository.RestaurantRepository;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemController(MenuItemRepository menuItemRepository,
                              RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // ───────────── GET ALL ─────────────
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // ───────────── GET BY ID ─────────────
    @GetMapping("/{id}")
    public Optional<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id);
    }

    // ───────────── CREATE (NEW CLEAN WAY) ─────────────
    @PostMapping("/restaurant/{restaurantId}")
    public MenuItem addMenuItemToRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> body) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem item = new MenuItem();
        item.setRestaurant(restaurant);

        if (body.get("itemName") != null) {
            item.setItemName((String) body.get("itemName"));
        }

        if (body.get("description") != null) {
            item.setDescription((String) body.get("description"));
        }

        if (body.get("celiacCertified") != null) {
            item.setCeliacCertified((Boolean) body.get("celiacCertified"));
        }

        return menuItemRepository.save(item);
    }

    // ───────────── UPDATE ─────────────
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {

        return menuItemRepository.findById(id).map(item -> {

            if (body.get("itemName") != null) {
                item.setItemName((String) body.get("itemName"));
            }

            if (body.get("description") != null) {
                item.setDescription((String) body.get("description"));
            }

            if (body.get("celiacCertified") != null) {
                item.setCeliacCertified((Boolean) body.get("celiacCertified"));
            }

            return menuItemRepository.save(item);

        }).orElseThrow(() -> new RuntimeException("Menu item not found with id " + id));
    }

    // ───────────── DELETE ─────────────
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
    }
}