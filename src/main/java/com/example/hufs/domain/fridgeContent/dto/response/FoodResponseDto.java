package com.example.hufs.domain.fridgeContent.dto.response;

import lombok.Builder;

@Builder
public record FoodResponseDto(
        Long foodId,
        String foodName,
        String imageUrl
) {
}
