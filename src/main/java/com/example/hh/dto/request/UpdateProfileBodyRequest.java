package com.example.hh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UpdateProfileBodyRequest {

    private String userName;

    private String description;

    private String email;

    private MultipartFile image;
}
