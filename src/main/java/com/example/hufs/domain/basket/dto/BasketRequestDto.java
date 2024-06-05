package com.example.hufs.domain.basket.dto;

public record BasketRequestDto(
        Long productId,
        Integer price,
        Integer quantity
) {
}
