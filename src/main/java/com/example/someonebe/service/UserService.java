package com.example.someonebe.service;

import com.example.someonebe.dto.request.CheckNicknameRequestDto;
import com.example.someonebe.dto.response.CheckNicknameResponseDto;
import com.example.someonebe.dto.request.CheckEmailRequestDto;
import com.example.someonebe.dto.request.LoginRequestDto;
import com.example.someonebe.dto.response.CheckEmailResponseDto;
import com.example.someonebe.dto.response.LoginResponseDto;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.request.SignupRequestDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.dto.response.MessageResponseDto;
import com.example.someonebe.dto.request.SignupRequestDto;
import com.example.someonebe.dto.response.StatusEnum;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.jwt.JwtUtil;
import com.example.someonebe.jwt.UserRoleEnum;
import com.example.someonebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "admin";

    @Transactional
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER);
        }

        Optional<User> nickfind = userRepository.findByNickname(signupRequestDto.getNickname());
        if (nickfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_NICKNAME);
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        userRepository.save(new User(username, password, nickname, role));
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException(ExceptionEnum.PASSWORD_MISMATCH);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(
                user.getUsername(),
                user.getRole(),
                user.getNickname()));
        return new LoginResponseDto(StatusEnum.OK, user.getNickname(), "null");
//        return new MessageResponseDto(StatusEnum.OK, null);
    }
    
    @Transactional(readOnly = true)
    public CheckEmailResponseDto confirmEmail(CheckEmailRequestDto checkEmailRequestDto) {

        String username = checkEmailRequestDto.getEmail();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_USER);
        }

        return new CheckEmailResponseDto(StatusEnum.OK,"pass", "null");
    }

    @Transactional(readOnly = true)
    public CheckNicknameResponseDto confirmNickname(CheckNicknameRequestDto checkNicknameRequestDto) {
        String nickname =checkNicknameRequestDto.getNickname();

        Optional<User> nicknamefind = userRepository.findByNickname(nickname);
        if(nicknamefind.isPresent()) {
            throw new ApiException(ExceptionEnum.DUPLICATE_NICKNAME);
        }
        return new CheckNicknameResponseDto(StatusEnum.OK,"pass", "null");
    }
}
