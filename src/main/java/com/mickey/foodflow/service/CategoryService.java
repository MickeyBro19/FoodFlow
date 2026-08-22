package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.CategoryRequest;
import com.mickey.foodflow.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);
    CategoryResponse getCategoryByName(String name);

    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}
