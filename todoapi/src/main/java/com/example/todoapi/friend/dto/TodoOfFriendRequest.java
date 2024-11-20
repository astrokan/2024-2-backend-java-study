package com.example.todoapi.friend.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TodoOfFriendRequest {
    @NotNull(message = ErrorMessage.FRIEND_ID_NOT_NULL)
    private Long friendId;
    @NotNull(message = ErrorMessage.MEMBER_ID_NOT_NULL)
    private Long memberId;
}
