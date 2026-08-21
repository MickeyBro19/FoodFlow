package com.mickey.foodflow.dto;

import com.mickey.foodflow.enums.Status;
import lombok.Builder;

import lombok.Getter;


@Getter
@Builder
public class RestaurantResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String city;
    private double rating;
    private Status status;

}
