package com.example.someonebe.controller;


import com.example.someonebe.dto.request.CheckNicknameRequestDto;
import com.example.someonebe.dto.response.CheckNicknameResponseDto;
import com.example.someonebe.dto.request.CheckEmailRequestDto;
import com.example.someonebe.dto.request.LoginRequestDto;
import com.example.someonebe.dto.response.CheckEmailResponseDto;
import com.example.someonebe.dto.response.LoginResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.request.SignupRequestDto;
import com.example.someonebe.jwt.JwtUtil;
import com.example.someonebe.kakao.KakaoService;
import com.example.someonebe.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;

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
    
    @PostMapping("/confirm-email")
    public CheckEmailResponseDto confirmEmail(
            @RequestBody @Valid CheckEmailRequestDto checkEmailRequestDto) {
        return userService.confirmEmail(checkEmailRequestDto);
    }

    @PostMapping("/confirm-nickname")
    public CheckNicknameResponseDto confirmNickname(
            @RequestBody @Valid CheckNicknameRequestDto checkNicknameRequestDto
            ) {
        return userService.confirmNickname(checkNicknameRequestDto);
    }


    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code, response);

        // Cookie 생성 및 직접 브라우저에 Set
        // 서버에서 자동으로쿠키를 만들어넣고 저장시킴 -> 프론트와 협의 가능
        // 클라이언트에서 받아서 저장할거면 service의 4번 주석부분 풀기
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/users/login";
    }

}
