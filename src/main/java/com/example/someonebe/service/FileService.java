package com.example.someonebe.service;

import com.example.someonebe.dto.request.ReqFileDto;
import com.example.someonebe.util.S3Uploader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service @RequiredArgsConstructor
@Slf4j
public class FileService {

    private final S3Uploader s3Uploader;

    @Value("${com.example.upload.path}")
    private String uploadPath;

    public void uploadFiles(ReqFileDto dto){

        MultipartFile[] files = dto.getFiles();

        List<Path> savePaths = new ArrayList<>();

        for(MultipartFile file : files){

            Objects.requireNonNull(file);

            //요청 받은 업로드 파일 중 image 타입만 허용
            if (file.getContentType().startsWith("image")){

                String originalName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

                try {
                    file.transferTo(savePath);
                } catch (IOException e) {
                    log.info("S3 file upload Fail");
                }

                savePaths.add(savePath);
            }
        }


        for(Path p : savePaths){

            s3Uploader.uploadFileToS3(uploadPath + p.getFileName().toString());
        }

        //중복된 파일 이름을 허용하기 위한 uuid와 기존 파일 이름의 결합

    }

}
