package com.example.todoapi.member.dto;

import com.example.todoapi.common.message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberLoginRequest {

    @NotNull(message = ErrorMessage.LOGIN_ID_NOT_NULL)
    @Length(min=3, max=15, message = "아이디는 3자 이상 15자 이하입니다.")
    private String loginId;

    @NotNull(message = ErrorMessage.LOGIN_PASSWORD_NOT_NULL)
    @Length(min=5, max=20, message = "비밀번호는 5자 이상 20자 이하입니다.")
    private String password;
}

