package com.example.todoapi.todo.dto;

import lombok.Getter;

@Getter
public class TodoUpdateContentRequest {
    private Long memberId;
    private String updateContent;
}

