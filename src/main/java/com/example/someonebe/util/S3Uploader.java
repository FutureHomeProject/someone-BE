package com.example.someonebe.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class S3Uploader {

    public final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String uploadFileToS3(String filePath) {

        File targetFile = new File(filePath);

        String uploadImageUrl = putS3(targetFile.getName(), targetFile);

        removeOriginalFile(targetFile);

        return uploadImageUrl;

    }

    public String getFileUrl(String fileName) {

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void removeS3File(String fileName) {

        amazonS3Client.deleteObject(bucket, fileName);
    }

    private void removeOriginalFile(File targetFile) {

        if (targetFile.exists() && targetFile.delete()) {
            log.info("File delete success");

            return;
        }

        log.info("fail to remove");

    }

    private String putS3(String filename, File uploadFile) throws RuntimeException {

        amazonS3Client.putObject(new PutObjectRequest(bucket, filename, uploadFile).withCannedAcl(
            CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, filename).toString();
    }


}
