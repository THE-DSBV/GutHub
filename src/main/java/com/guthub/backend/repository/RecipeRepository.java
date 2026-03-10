package com.guthub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guthub.backend.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query
    (value = "SELECT * FROM recipe WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Recipe> searchRecipesByKeyword(@Param("keyword") String keyword);
}
