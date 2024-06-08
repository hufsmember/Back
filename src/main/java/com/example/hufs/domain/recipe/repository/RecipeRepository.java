package com.example.hufs.domain.recipe.repository;

import com.example.hufs.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByMember_Id(Long memberId);
}
