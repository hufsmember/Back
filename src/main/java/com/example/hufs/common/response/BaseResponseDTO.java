package com.example.hufs.common.response;

import com.example.hufs.common.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class BaseResponseDTO<T> {

    private Boolean isSuccess;
    private int statusCode;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) //data가 null이면 json 직렬화에서 제외하고, 출력되지 않도록 지정해줌.
    private T data;

    public static BaseResponseDTO<Void> ok() { //보이드는 data가 없음을 명시
        return BaseResponseDTO.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .data(null)
                .message(null)
                .build();
    }

    public static <T> BaseResponseDTO<T> okWithData(T data){ //제너릭 메서드에서 <T>를 사용하겠다고 static 뒤에 <T>를 명시
        return BaseResponseDTO.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .message(null)
                .build();
    }

    public static <T> BaseResponseDTO<T> okWithDataAndMessage(String message, T data) {
        return BaseResponseDTO.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }

    public static BaseResponseDTO<Void> error(ExceptionType exceptionType){
        return BaseResponseDTO.<Void>builder()
                .statusCode(exceptionType.getHttpStatus().value())
                .message(exceptionType.getMessage())
                .data(null)
                .build();
    }

    public static <T> BaseResponseDTO<T> errorWithData(ExceptionType exceptionType, T data){
        return BaseResponseDTO.<T>builder()
                .statusCode(exceptionType.getHttpStatus().value())
                .message(null)
                .data(data)
                .build();
    }

    public static BaseResponseDTO<Void> errorWithMessage(ExceptionType exceptionType){
        return BaseResponseDTO.<Void>builder()
                .statusCode(exceptionType.getHttpStatus().value())
                .message(exceptionType.getMessage())
                .data(null)
                .build();
    }


    public static <T> BaseResponseDTO<T> errorWithDataAndMessage(ExceptionType exceptionType, T data){
        return BaseResponseDTO.<T>builder()
                .statusCode(exceptionType.getHttpStatus().value())
                .message(exceptionType.getMessage())
                .data(data)
                .build();
    }


}
