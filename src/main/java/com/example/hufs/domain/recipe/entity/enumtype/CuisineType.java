package com.example.hufs.domain.recipe.entity.enumtype;

import lombok.Getter;

@Getter
public enum CuisineType {

    ASIAN("아시안"),
    KOREAN("한식"),
    WESTERN("양식"),
    EUROPEAN("유럽식"),
    CHINESE("중식"),
    JAPANESE("일식");

    private final String type;

    CuisineType(String type){this.type = type;}
}
