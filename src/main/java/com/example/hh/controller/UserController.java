package com.example.hh.controller;

import com.example.hh.dto.request.JoinRequest;
import com.example.hh.dto.request.LoginRequest;
import com.example.hh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void join(@RequestBody JoinRequest join){
        userService.join(join);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest){ userService.login(loginRequest); }


}