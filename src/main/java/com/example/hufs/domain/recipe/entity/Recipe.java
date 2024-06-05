package com.example.hufs.domain.recipe.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @Column
    private String recipeName;

    @Column
    private String cuisineType;

    @Column
    private String description;

    @Column
    private String recipeImageUrl;

    @Column
    private Boolean isVegan;

    @Column
    private LocalDateTime deletedAt;
}
