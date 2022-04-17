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

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody JoinRequest join){
        userService.join(join);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){ return userService.login(loginRequest); }

    @GetMapping("/posts") @PreAuthorize("isAuthenticated()")
    public List<GetUserPostResponse> getUserPost(Pageable pageable){return userService.responses(pageable);}

    @GetMapping("/profile") @PreAuthorize("isAuthenticated()")
    public GetProfileResponse profile(){return userService.getProfileResponse();}

    @PutMapping("/profile") @PreAuthorize("isAuthenticated()")
    public void updateProfile(@RequestBody UpdateProfileBodyRequest update){userService.updateProfile(update);}

}