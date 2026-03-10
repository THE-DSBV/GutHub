package com.guthub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guthub.backend.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query
    (value = "SELECT * FROM restaurant WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Restaurant> searchRestaurantsByKeyword(@Param("keyword") String keyword);

    @Query
    (value = "SELECT * FROM restaurant WHERE rating >= :minRating", nativeQuery = true)
    List<Restaurant> findByRatingGreaterThanEqual(@Param("minRating") Integer minRating);
    
    
    }
    


