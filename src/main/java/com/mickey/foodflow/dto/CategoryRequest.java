package com.mickey.foodflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Please enter a category name")
    @Size(min = 2,max = 50,message = "Category name must be between 2 and 50 characters")
    private String name;
}
