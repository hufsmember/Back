package com.example.hufs.domain.food.entity.enumtype;

import lombok.Getter;

@Getter
public enum StorageMethod {
    REFRIGERATED("냉장"),
    FROZEN("냉동");

    private final String type;

    StorageMethod(String type) { this.type = type; }

    }
