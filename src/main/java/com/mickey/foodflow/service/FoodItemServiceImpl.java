package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.FoodItemRequest;
import com.mickey.foodflow.dto.FoodItemResponse;
import com.mickey.foodflow.entity.Category;
import com.mickey.foodflow.entity.FoodItem;
import com.mickey.foodflow.entity.Restaurant;
import com.mickey.foodflow.enums.Availability;
import com.mickey.foodflow.exception.ResourceNotFoundException;
import com.mickey.foodflow.repository.CategoryRepository;
import com.mickey.foodflow.repository.FoodItemRepository;
import com.mickey.foodflow.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    private FoodItemResponse mapToResponse(FoodItem foodItem){
        return FoodItemResponse.builder()
                .id(foodItem.getId())
                .name(foodItem.getName())
                .description(foodItem.getDescription())
                .price(foodItem.getPrice())
                .availability(foodItem.getAvailability())
                .categoryId(foodItem.getCategory().getId())
                .categoryName(foodItem.getCategory().getName())
                .restaurantId(foodItem.getRestaurant().getId())
                .restaurantName(foodItem.getRestaurant().getName())
                .build();
    }

    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest request) {
        Category category=categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category id: "+ request.getCategoryId()+" invalid"));
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant id: "+ request.getRestaurantId()+" invalid"));
        FoodItem foodItem= FoodItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .availability(request.getAvailability()!=null?request.getAvailability(): Availability.AVAILABLE)
                .category(category)
                .restaurant(restaurant)
                .build();
        FoodItem saved=foodItemRepository.save(foodItem);
        FoodItem result =
                foodItemRepository.findByIdWithDetails(saved.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Food item not found after creation"
                                )
                        );
        return mapToResponse(result);
    }

    @Override
    public List<FoodItemResponse> getAllFoodItems() {
        return foodItemRepository.findAllWithCategoryAndRestaurant().stream().map(this::mapToResponse).toList();
    }

    @Override
    public FoodItemResponse getFoodItemById(Long id) {
        FoodItem foodItem=foodItemRepository.findByIdWithDetails(id).orElseThrow(()->new ResourceNotFoundException("Food item id: "+id+" invalid"));
        return mapToResponse(foodItem);
    }

    @Override
    public List<FoodItemResponse> getFoodItemByRestaurant(Long restaurantId) {
        return foodItemRepository.findByRestaurantIdWithDetails(restaurantId).stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<FoodItemResponse> getFoodItemByCategory(Long categoryId) {
        return foodItemRepository.findByCategoryIdWithDetails(categoryId).stream().map(this::mapToResponse).toList();
    }

    @Override
    public FoodItemResponse updateFoodItem(Long id, FoodItemRequest request) {
        FoodItem foodItem=foodItemRepository.findByIdWithDetails(id).orElseThrow(()->new ResourceNotFoundException("Food item id: "+id+" invalid"));
        Category category=categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category id: "+ request.getCategoryId()+" invalid"));
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant id: "+ request.getRestaurantId()+" invalid"));

        foodItem.setName(request.getName());
        foodItem.setDescription(request.getDescription());
        foodItem.setPrice(request.getPrice());
        foodItem.setAvailability(request.getAvailability()!=null?request.getAvailability(): Availability.AVAILABLE);
        foodItem.setCategory(category);
        foodItem.setRestaurant(restaurant);

        FoodItem updated= foodItemRepository.save(foodItem);
        FoodItem result=foodItemRepository.findByIdWithDetails(updated.getId()).orElseThrow(()->new ResourceNotFoundException("Food item id: "+id+" was not found after update"));

        return mapToResponse(result);
    }

    @Override
    public void deleteFoodItem(Long id) {
        FoodItem foodItem=foodItemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Food item id: "+id+" invalid"));
        foodItemRepository.delete(foodItem);
    }
}
