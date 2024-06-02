package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RecommendRecipeResponseDto(
        String memberName,
        List<RecommendRecipeStringImageDto> basedOnFridge,
        List<RecommendRecipeStringImageDto> basedOnPreference
) {
}
