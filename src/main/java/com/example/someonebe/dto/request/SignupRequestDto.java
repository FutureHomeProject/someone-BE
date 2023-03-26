package com.example.someonebe.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "유효한 이메일 주소를 입력해 주세요.")
    private String email;

    @NotBlank(message = "비빌번호를 입력해주세요.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하의 숫자또는 알파벳 대소문자를 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 입력 가능합니다.")
//    /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+{}|:;<>?,./\\])(?!.*\s).{8,}$/
    private String password;

    @NotNull(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 15, message = "닉네임은 2~15자리까지 입력해주세요.")
    private String nickname;

    private boolean admin = false;

    private String adminToken = "";
}

