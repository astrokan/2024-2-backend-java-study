package com.example.todoapi.common.exception;

// 존재하는 정보를 요청하였으나, 정책 상 허용하지 않는 접근을 시도함.
public class BadDataAccessException extends RuntimeException {

    public BadDataAccessException(String message) {
        super(message);
    }
}
