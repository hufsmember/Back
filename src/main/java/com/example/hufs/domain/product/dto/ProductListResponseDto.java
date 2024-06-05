package com.example.hufs.domain.product.dto;

import lombok.Builder;

@Builder
public record ProductListResponseDto(
        Long productId,
        Integer quantity,
        String imageUrl
) {
}
