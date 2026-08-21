package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.RestaurantRequest;
import com.mickey.foodflow.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse getRestaurantById(Long id);
    RestaurantResponse updateRestaurant(Long id,RestaurantRequest restaurantRequest);
    void deleteRestaurant(Long id);
}
