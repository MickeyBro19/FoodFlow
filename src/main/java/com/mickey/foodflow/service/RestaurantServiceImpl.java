package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.RestaurantRequest;
import com.mickey.foodflow.dto.RestaurantResponse;
import com.mickey.foodflow.entity.Restaurant;
import com.mickey.foodflow.enums.Status;
import com.mickey.foodflow.exception.ResourceNotFoundException;
import com.mickey.foodflow.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    //rather than using the whole client info we use request and response dto to get , access and work with databases.
    //we don't want to expose the whole database to the client so we use dto's, also it provides flexibility to change database model without breaking the API contracts.
    private RestaurantResponse mapToResponse(Restaurant saved) {
        return RestaurantResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .address(saved.getAddress())
                .city(saved.getCity())
                .rating(saved.getRating())
                .status(saved.getStatus())
                .build();
    }

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant=Restaurant.builder()
                .name(restaurantRequest.getName())
                .description(restaurantRequest.getDescription())
                .address(restaurantRequest.getAddress())
                .city(restaurantRequest.getCity())
                .rating(0.0)
                .status(Status.ACTIVE)
                .build();
        Restaurant saved=restaurantRepository.save(restaurant);
        return mapToResponse(saved);
    }



    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        Restaurant found= restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with id: "+ id));
        return mapToResponse(found);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant found= restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with id: "+ id));
        found.setName(restaurantRequest.getName());
        found.setDescription(restaurantRequest.getDescription());
        found.setAddress(restaurantRequest.getAddress());
        found.setCity(restaurantRequest.getCity());
        Restaurant updated =restaurantRepository.save(found);
        return mapToResponse(updated);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant found= restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with id: "+ id));
        restaurantRepository.delete(found);


    }
}
