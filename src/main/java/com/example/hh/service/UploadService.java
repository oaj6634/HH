package com.example.hh.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

// 인터페이스로 나눈 이유는 FildUploadService에서 AWSS3UploadService가 아닌 UploadService와 의존관계를 맺음으로써 추후에 다른 로직을 구현할 때 유연하게 변경할 수 있기 때문에
public interface UploadService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    String getUrl(String fileName);
}
