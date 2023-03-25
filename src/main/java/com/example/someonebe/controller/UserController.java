package com.example.someonebe.controller;

import com.example.someonebe.dto.request.LoginRequestDto;
import com.example.someonebe.dto.response.LoginResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.request.SignupRequestDto;
import com.example.someonebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public MessageResponseDto signup(
            @Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
