package com.example.someonebe.util;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class S3UploaderTest {

    @Autowired
    private S3Uploader s3Uploader;
    
    @Test
    @DisplayName("기본적인 Uploader 실행 테스트")
    public void loadTest(){
        /* given */
        
        /* when */
    
        /* then */
        System.out.println(s3Uploader.amazonS3Client);
    }

    @Test
    public void upload(){

        /* given */
        String filePath = "C:\\test\\2372ad79e18d7b.png";

        /* when */
        String uploadName= s3Uploader.uploadFileToS3(filePath);
        /* then */

        System.out.println(uploadName);
    }

    @Test @DisplayName("S3로부터 해당 file 이름의 객체 Url를 얻어 옴")
    public void getImage() throws IOException {

        /* given */
        String fileName = "2372ad79e18d7b.png";

        /* when */

        String url = s3Uploader.getFileUrl(fileName);

        /* then */

        Assertions.assertNotNull(url);
        System.out.println(url);
    }
    
    @Test @DisplayName("S3에 저장된 file을 filename으로 삭제 함")
    public void deleteS3File(){
        /* given */
        String fileName = "2372ad79e18d7b.png";

        /* when */

        s3Uploader.removeS3File(fileName);

        /* then */
    }

}