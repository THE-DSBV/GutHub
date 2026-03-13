package com.guthub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guthub.backend.model.RecipeReview;

public interface RecipeReviewRepository extends JpaRepository<RecipeReview, Long> {

}
