package com.example.hh.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.hh.aws.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AWSS3UploadService implements UploadService{

    private final AmazonS3 amazonS3;
    private final S3Component s3Component;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        //마지막에 withCannedAcl로 선택적 엑세스제어정책을 설정한다는데 엑세스제어정책이란 보호된 자원에 대한 엑세스가 사용자에게 허용되는지 거부되는지 정의하는 것이라고 함.
        amazonS3.putObject(new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }
    @Override
    public String getUrl(String fileName){
        return amazonS3.getUrl(s3Component.getBucket(), fileName).toString();
    }
}
