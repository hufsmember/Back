package com.example.hufs.domain.recipeIngrendient.repository;

import com.example.hufs.domain.recipe.entity.Recipe;
import com.example.hufs.domain.recipeIngrendient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByRecipe(Recipe recipe);
}
