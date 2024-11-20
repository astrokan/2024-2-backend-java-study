package com.example.todoapi.todo.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoUpdateContentRequest {
    @NotNull(message = ErrorMessage.MEMBER_ID_NOT_NULL)
    private Long memberId;
    @Length(max = 200, message = ErrorMessage.LENGTH_OF_CONTENT_SHOULD_NOT_EXCEED)
    private String updateContent;
}

