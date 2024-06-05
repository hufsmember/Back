package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

@Builder
public record RecommendRecipeResponseDto(
        Long recipeId,
        String imageUrl,
        String cuisineName
) {
}
