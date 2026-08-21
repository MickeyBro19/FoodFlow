package com.mickey.foodflow.repository;

import com.mickey.foodflow.entity.Category;
import com.mickey.foodflow.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    List<FoodItem> findByRestaurantRating(Long restaurantId);

    List<FoodItem> findByCategory(Long categoryId);
}
