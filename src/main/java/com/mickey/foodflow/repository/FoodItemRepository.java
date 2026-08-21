package com.mickey.foodflow.repository;

import com.mickey.foodflow.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    @Query("""
    SELECT f from FoodItem f
    join fetch  f.category
    join fetch f.restaurant
""")
    List<FoodItem> findAllWithCategoryAndRestaurant();

    @Query("""
    select f from FoodItem f
    join fetch f.category
    join fetch f.restaurant
    where f.restaurant.id= :restaurantId
""")
    List<FoodItem> findByRestaurantIdWithDetails(Long restaurantId);

    @Query("""
    select f from FoodItem f
    join fetch f.category
    join fetch f.restaurant
    where f.category.id= :categoryId
""")
    List<FoodItem> findByCategoryIdWithDetails(Long categoryId);

    @Query("""
    select f from FoodItem f
    join fetch f.category
    join fetch f.restaurant
    where f.id=:foodId
""")
    Optional<FoodItem> findByIdWithDetails(Long foodId);
}
