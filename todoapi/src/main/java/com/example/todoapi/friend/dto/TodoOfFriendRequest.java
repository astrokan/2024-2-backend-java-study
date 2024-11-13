package com.example.todoapi.friend.dto;

import lombok.Getter;

@Getter
public class TodoOfFriendRequest {
    private Long friendId;
    private Long memberId;
}
