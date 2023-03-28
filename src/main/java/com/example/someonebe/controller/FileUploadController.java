//package com.example.someonebe.controller;
//
//import com.example.someonebe.dto.request.ReqFileDto;
//import com.example.someonebe.service.FileService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Log4j2
//@RequiredArgsConstructor
//public class FileUploadController {
//
//    private final FileService fileService;
//
//    @PostMapping(value = "/test/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Object> upload(ReqFileDto reqFileDto) {
//
//        fileService.uploadFiles(reqFileDto);
//
//        return ResponseEntity.ok("등록 완료");
//    }
//
//}
