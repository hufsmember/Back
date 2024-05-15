package com.example.hufs.member.domain.entity.enumtype;

import lombok.Getter;

@Getter
public enum AgeGroup {
    TEEN("10대"),
    TWENTY("20대"),
    THIRTY("30대"),
    FORTY("40대"),
    FIFTY("50대"),
    SIXTY("60대"),
    SEVENTY("70대"),
    EIGHTY("80대"),
    NINETY("90대"),
    HUNDRED("100대");

    private final String type;

    AgeGroup(String type) { this.type = type; }

}
