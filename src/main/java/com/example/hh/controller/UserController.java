package com.example.hh.controller;

import com.example.hh.domain.Post;
import com.example.hh.domain.User;
import com.example.hh.dto.request.*;
import com.example.hh.dto.response.GetProfileResponse;
import com.example.hh.dto.response.GetUserPostResponse;
import com.example.hh.dto.response.LoginResponse;
import com.example.hh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void join(@RequestBody JoinRequest join) {
        userService.join(join);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/posts")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserPostResponse> getUserPost(Pageable pageable) {
        return userService.getUserPost(pageable);
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public GetProfileResponse getProfile() {
        return userService.getProfile();
    }

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfile(@RequestPart("file") UpdateProfileBodyRequest profileImage,
                                        @RequestPart("description") UpdateProfileBodyRequest profileDescription,
                                        @RequestPart("userName") UpdateProfileBodyRequest profileUserName) {
        userService.updateProfile(profileImage, profileDescription, profileUserName);
    }

}