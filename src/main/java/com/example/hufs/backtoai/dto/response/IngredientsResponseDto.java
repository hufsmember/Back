package com.example.hufs.backtoai.dto.response;

import lombok.Builder;

@Builder
public record IngredientsResponseDto(
        String ingredient,
        Integer quantity
) {

}
