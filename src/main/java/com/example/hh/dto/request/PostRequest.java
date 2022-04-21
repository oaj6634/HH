package com.example.hh.dto.request;

import com.example.hh.domain.User;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostRequest {

    private MultipartFile multipartFile;

    private String strContent;

    private String strTitle;

}
