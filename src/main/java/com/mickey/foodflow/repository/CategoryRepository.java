package com.mickey.foodflow.repository;

import com.mickey.foodflow.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import tools.jackson.core.util.Named;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByNameIgnoreCase(String name);
    boolean existsByName(String name);
}
