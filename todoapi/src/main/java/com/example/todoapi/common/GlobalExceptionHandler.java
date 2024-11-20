package com.example.todoapi.common;

import com.example.todoapi.common.dto.ErrorResponse;
import com.example.todoapi.common.exception.BadDataAccessException;
import com.example.todoapi.common.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
    // 비즈니스 로직 상 BadRequestException 에러 클래스로 에러를 발생시켰을 때 처리(400)
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    // 비즈니스 로직 상 BadDataAccessException 에러 클래스로 에러를 발생시켰을 때 처리(400)
    @ExceptionHandler(value = BadDataAccessException.class)
    public ResponseEntity<ErrorResponse> handleBadDataAccessException(BadDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    // dto에서 유효성 검사 실패했을 때 대신 응답하는 핸들러(400)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
