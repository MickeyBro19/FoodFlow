package com.mickey.foodflow.controller;

import com.mickey.foodflow.dto.FoodItemRequest;
import com.mickey.foodflow.dto.FoodItemResponse;
import com.mickey.foodflow.service.FoodItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fooditems")
public class FoodItemController {
    private final FoodItemService foodItemService;

    @GetMapping
    public ResponseEntity<List<FoodItemResponse>> getAllFoodItems(){
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @PostMapping
    public ResponseEntity<FoodItemResponse> addFoodItem(@Valid @RequestBody FoodItemRequest foodItemRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemService.createFoodItem(foodItemRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemResponse>  getFoodItemById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getFoodItemById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<FoodItemResponse>>  getFoodItemByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getFoodItemByCategory(categoryId));
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItemResponse>>  getFoodItemByRestaurantId(@PathVariable Long restaurantId){
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getFoodItemByRestaurant(restaurantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemResponse> updateFoodItem(@PathVariable Long id,@Valid @RequestBody FoodItemRequest foodItemRequest){
        return ResponseEntity.status(HttpStatus.OK).body(foodItemService.updateFoodItem(id,foodItemRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id){
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
}
