package com.guthub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guthub.backend.model.RestaurantReview;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {

}
