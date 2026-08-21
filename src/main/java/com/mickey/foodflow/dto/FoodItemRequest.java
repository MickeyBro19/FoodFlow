package com.mickey.foodflow.dto;

import com.mickey.foodflow.enums.Availability;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FoodItemRequest {
    @NotBlank(message = "Name required")
    @Size(min = 2,max = 50,message = "Name should be between 2 ang 50 characters")
    private String name;

    @Size(max = 1000 , message = "Description should not exceed 1000 characters")
    private String description;

    @NotNull(message = "Price Required")
    @DecimalMin(
            value = "0.01",
            message = "Price must be greater than zero"
    )
    private BigDecimal price;

    private Availability availability;

    @NotNull(message = "Category ID required")
    @Positive(message = "Category Id must be positive")
    private Long categoryId;

    @NotNull(message = "Restaurant ID required")
    @Positive(message = "Restaurant Id must be positive")
    private Long restaurantId;

}
