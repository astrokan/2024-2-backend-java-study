package com.example.todoapi.friend.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FriendInfoRequest {
    @NotNull(message = ErrorMessage.SENDER_ID_NOT_NULL)
    private Long senderId;
    @NotNull(message = ErrorMessage.RECEIVER_ID_NOT_NULL)
    private Long receiverId;
}
