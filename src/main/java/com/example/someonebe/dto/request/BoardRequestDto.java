package com.example.someonebe.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

//    @NotBlank(message = "주거형식을 입력해주세요.")
    private String dwellingtype;

//    @NotBlank(message = "평수를 입력해주세요.")
    private String average;

//    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

//    @NotNull(message = "이미지를 업로드해주세요.")
    private MultipartFile image;

//    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;

//    @NotBlank(message = "지역을 입력해주세요.")
    private String region;
}
