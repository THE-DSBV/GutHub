package com.guthub.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.MenuItem;
import com.guthub.backend.repository.MenuItemRepository;
import com.guthub.backend.repository.RestaurantRepository;

// Available API endpoints:
// GET    /menu-items                                        - get all menu items
// GET    /menu-items/{id}                                   - get menu item by id
// POST   /menu-items                                        - create a menu item
// PUT    /menu-items/{id}                                   - update a menu item
// DELETE /menu-items/{id}                                   - delete a menu item
// GET    /menu-items/restaurant/{restaurantId}              - get all items for a restaurant
// GET    /menu-items/restaurant/{restaurantId}/gluten-free  - get gluten-free items for a restaurant
// GET    /menu-items/restaurant/{restaurantId}/celiac       - get celiac certified items for a restaurant
// GET    /menu-items/restaurant/{restaurantId}/search       - search items by name within a restaurant
// GET    /menu-items/search?keyword={keyword}               - search items by name across all restaurants
// GET    /menu-items/celiac                                 - get all celiac certified items across all restaurants

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

    // GET all menu items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // GET single menu item by id
    @GetMapping("/{id}")
    public Optional<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id);
    }

    // POST create a new menu item (restaurant must already exist)
    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // PUT update an existing menu item
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updatedItem) {
        return menuItemRepository.findById(id).map(item -> {
            item.setItemName(updatedItem.getItemName());
            item.setDescription(updatedItem.getDescription());
            item.setCeliacCertified(updatedItem.isCeliacCertified());
            item.setRestaurant(updatedItem.getRestaurant());
            return menuItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Menu item not found with id " + id));
    }

    // DELETE a menu item
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
    }

    // GET all menu items for a specific restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    // GET search items by name across all restaurants
    @GetMapping("/search")
    public List<MenuItem> searchAllItems(@RequestParam String keyword) {
        return menuItemRepository.searchByItemName(keyword);
    }

    // GET celiac certified items for a specific restaurant
    @GetMapping("/restaurant/{restaurantId}/celiac")
    public List<MenuItem> getCeliacCertifiedItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemRepository.findCeliacCertifiedByRestaurantId(restaurantId);
    }

}