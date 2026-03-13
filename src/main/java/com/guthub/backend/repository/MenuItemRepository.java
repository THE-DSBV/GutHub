package com.guthub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guthub.backend.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query(value = "SELECT * FROM menu_item WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<MenuItem> findByRestaurantId(@Param("restaurantId") Long restaurantId);


    @Query(value = "SELECT * FROM menu_item WHERE restaurant_id = :restaurantId AND gluten_free = TRUE", nativeQuery = true)
    List<MenuItem> findGlutenFreeByRestaurantId(@Param("restaurantId") Long restaurantId);


    @Query(value = "SELECT * FROM menu_item WHERE restaurant_id = :restaurantId AND celiac_certified = TRUE", nativeQuery = true)
    List<MenuItem> findCeliacCertifiedByRestaurantId(@Param("restaurantId") Long restaurantId);


    @Query(value = "SELECT * FROM menu_item WHERE LOWER(item_name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<MenuItem> searchByItemName(@Param("keyword") String keyword);

}