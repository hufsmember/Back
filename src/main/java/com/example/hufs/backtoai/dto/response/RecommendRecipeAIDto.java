package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

@Builder
public record RecommendRecipeAIDto(
        Long recipeId,
        String recipeName,
        String description,
        String imageUrl,
        String cuisineType,
        Boolean isVegan,
        String ingredient,
        Integer quantity
) {
}
