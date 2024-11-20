package com.example.todoapi.common.exception;

// 존재하지 않는 정보를 요청
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
