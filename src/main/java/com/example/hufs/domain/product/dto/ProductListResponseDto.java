package com.example.hufs.domain.product.dto;

import lombok.Builder;

@Builder
public record ProductListResponseDto(
        Long productId,
        String productName,
        Integer price,
        String imageUrl
) {
}
