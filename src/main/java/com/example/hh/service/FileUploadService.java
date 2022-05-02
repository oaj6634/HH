package com.example.hh.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final UploadService s3Service;

    // MultipartFile 은 쉽게말해서 파일을 받을 때 사용하는 클래스
    public String uploadImage(MultipartFile file) {
        //getOrginialFilename으로 클라이언트 파일시스템에 있는 원래 파일을 반환한다.
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
        }
        return s3Service.getUrl(fileName);
    }

    public String createFileName(String originalFileName) {
        //UUID는 유일한 식별자를 만드는 클래스라고 할 수 있다. .toString()으로 바꾸는 이유는 반환객체가 UUID객체이기 때문이다.
        // 식별자로 바꿔주는 이유는 s3에 저장할때 파일 이름이 같으면 에러가 나기 때문이다.
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식 파일 (%s) 입니다.", fileName));
        }
    }
}
