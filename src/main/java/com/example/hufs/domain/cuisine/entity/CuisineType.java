package com.example.hufs.domain.cuisine.entity;

import lombok.Getter;

@Getter
public enum CuisineType {
    WESTERN("양식"),
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    BUNSIK("분식"),
    DESSERT("디저트"),
    ASIAN("아시안");

    private final String type;

    CuisineType(String type){this.type = type;}
}
