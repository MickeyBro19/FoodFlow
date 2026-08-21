package com.mickey.foodflow.dto;

import com.mickey.foodflow.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


// we use DTOs to decouple the API contract from the persistence model, allows request-specific validation and prevent database implementation to APIs.

@Getter
public class RestaurantRequest {

    @NotBlank(message = "Restaurant name can't be empty")
    @Size(min = 2,max = 100,message = "name should be between 2 and 100 characters")
    private String name;

    @Size(max = 1000, message = "Max length 1000 characters")
    private String description;

    @NotBlank(message = "Address can't be empty")
    private String address;

    @NotBlank(message = "City can't be empty")
    private String city;

    private Status status;


}
