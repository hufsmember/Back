package com.example.hufs.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // MEMBER
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "토큰을 찾을 수 없습니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    DUPLICATED_MEMBER(HttpStatus.CONFLICT, "존재하는 사용자입니다."),

    // FRIDGE
    FRIDGE_NOT_EXIST(HttpStatus.NOT_FOUND, "냉장고를 찾을 수 없습니다."),
    FRIDGE_IS_EMPTY(HttpStatus.INTERNAL_SERVER_ERROR, "냉장고의 내용물이 비었습니다."),
    FRIDGE_NOT_FOUND_MEMBER_FOOD(HttpStatus.NOT_FOUND, "음식이 냉장고에 없습니다."),

    //FOOD
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "음식을 찾을 수 없습니다."),
    STORAGE_IS_DIFFERENT(HttpStatus.CONFLICT, "저장 방법이 다릅니다."),

    //RECIPE
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."),


    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final HttpStatus httpStatus;
    private final String message;

}
