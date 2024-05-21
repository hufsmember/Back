package com.example.hufs.domain.product.entity.enumtype;

import lombok.Getter;

@Getter
public enum ProductCategory {

    MEAT("고기"),
    FROZEN("냉동식품"),
    FRUIT("과일"),
    VEGETABLE("채소");

    private final String type;

    ProductCategory(String type){ this.type=type;}
}
