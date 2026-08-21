package com.mickey.foodflow.dto;

import com.mickey.foodflow.enums.Availability;
import com.mickey.foodflow.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class FoodItemResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Availability availability;

    private Long categoryId;
    private String categoryName;

    private Long restaurantId;
    private String restaurantName;
}
