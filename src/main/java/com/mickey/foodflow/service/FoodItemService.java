package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.FoodItemRequest;
import com.mickey.foodflow.dto.FoodItemResponse;

import java.util.List;

public interface FoodItemService {
    FoodItemResponse createFoodItem(FoodItemRequest request);
    List<FoodItemResponse> getAllFoodItems();
    FoodItemResponse getFoodItemById(Long id);
    List<FoodItemResponse> getFoodItemByRestaurant(Long restaurantId);
    List<FoodItemResponse> getFoodItemByCategory(Long categoryId);
    FoodItemResponse updateFoodItem(Long id, FoodItemRequest request);
    void deleteFoodItem(Long id);
}
