package com.example.todoapi.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_login_id")
    private String loginId;

    @Column(name = "user_pw")
    private String password;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}

