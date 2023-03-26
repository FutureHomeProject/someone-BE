package com.example.someonebe.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CheckNicknameRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 15, message = "닉네임은 2~15자리까지 입력해주세요.")
    private String nickname;
}
