package com.example.hufs.domain.recipeIngrendient.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.food.entity.Food;
import com.example.hufs.domain.recipe.entity.Recipe;
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
public class ReceipeIngredient extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column
    private Integer quantity;

    @Column
    private LocalDateTime deletedAt;
}
