package com.example.todoapi.friend.dto;

import lombok.Getter;

@Getter
public class FriendInfoRequest {
    private Long senderId;
    private Long receiverId;
}
