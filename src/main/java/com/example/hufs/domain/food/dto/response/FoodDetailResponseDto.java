package com.example.hufs.domain.food.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FoodDetailResponseDto(
        String storageMethod,
        LocalDate expiryDate,
        String foodName,
        Integer quantity,
        String imageUrl,
        Integer daysRemaining,
        LocalDate purchaseDate
) {
}
