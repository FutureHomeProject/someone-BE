package com.example.someonebe.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수사항 입니다.")
    @Size(min = 4, max = 10, message = "아이디는 최소 4자 이상, 10자 이하의 숫자또는 알파벳 소문자를 입력하세요")
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다.")
    private String loginid;

    @NotBlank(message = "비밀번호는 필수사항 입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하의 숫자또는 알파벳 대소문자를 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 입력 가능합니다.")
    private String password;

    @NotNull(message = "nickname은 필수사항 입니다.")
    private String nickname;

    private boolean admin = false;

    private String adminToken = "";
}

