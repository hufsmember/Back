package com.example.hufs.domain.member.entity.enumtype;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    SECRET("비밀"); //성별을 알리기 싫은 경우

    private final String type;

    Gender(String type) { this.type = type; }
}
