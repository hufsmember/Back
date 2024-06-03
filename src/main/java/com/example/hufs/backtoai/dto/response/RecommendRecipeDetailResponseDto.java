package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RecommendRecipeDetailResponseDto(
        String imageUrl,
        String recipeName,
        String description,
        List<IngredientsResponseDto> ingredients
) {
}
