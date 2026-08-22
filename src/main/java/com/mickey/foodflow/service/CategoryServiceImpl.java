package com.mickey.foodflow.service;

import com.mickey.foodflow.dto.CategoryRequest;
import com.mickey.foodflow.dto.CategoryResponse;
import com.mickey.foodflow.entity.Category;
import com.mickey.foodflow.exception.ResourceNotFoundException;
import com.mickey.foodflow.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private CategoryResponse mapToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(categoryRepository.existsByNameIgnoreCase(categoryRequest.getName())) throw new IllegalArgumentException("Category Already Exists "+categoryRequest.getName());
        Category category=Category.builder()
                .name(categoryRequest.getName())
                .build();
        Category saved=categoryRepository.save(category);
        return mapToResponse(saved);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: "+id));
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category=categoryRepository.findByNameIgnoreCase(name).orElseThrow(()->new ResourceNotFoundException("Category not found with name: "+name));
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: "+id));
        if(!category.getName().equalsIgnoreCase(categoryRequest.getName()) && categoryRepository.existsByNameIgnoreCase(categoryRequest.getName())) throw new IllegalArgumentException("Category Already exists "+categoryRequest.getName());
        category.setName(categoryRequest.getName().trim());

        return mapToResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: "+id));
        categoryRepository.delete(category);
    }
}
