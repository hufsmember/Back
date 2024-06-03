package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

@Builder
public record RecommendRecipeStringImageDto(
        String recipeId,
        String imageUrl,
        String cuisineName
) {
}
