package com.mickey.foodflow.entity;

import com.mickey.foodflow.enums.Availability;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_items")
@Builder
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;   //double can cause precision errors and money shouldn't be of floating-point

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Availability availability;

    @ManyToOne(fetch = FetchType.LAZY)  //fetch type lazy as it doesn't need to fetched at load, improving efficiency and speed
    @JoinColumn(name = "category_id", nullable = false) //multiple food items can be of one category type
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)   //multiple food items can belong to one restaurant
    private Restaurant restaurant;
}
