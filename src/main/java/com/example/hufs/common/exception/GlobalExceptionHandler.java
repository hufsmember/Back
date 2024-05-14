package com.example.hufs.common.exception;

import com.example.hufs.common.response.BaseResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //모든 컨트롤러에서 발생하는 예외를 처리(캐치해 올 수 있음)
@Slf4j
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponseDTO<Void>> baseExceptionHandler(BaseException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(BaseResponseDTO.error(e.getErrorCode()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponseDTO<Void>> bindException(BindException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponseDTO.errorWithMessage(HttpStatus.BAD_REQUEST,
                        e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<BaseResponseDTO<Void>> dbException(DataAccessException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseDTO.errorWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 에러!"));
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponseDTO<Void>> serverException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponseDTO.errorWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러!"));
    }
}
