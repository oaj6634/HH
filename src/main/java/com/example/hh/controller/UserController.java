package com.example.hh.controller;

import com.example.hh.domain.Post;
import com.example.hh.domain.User;
import com.example.hh.dto.request.*;
import com.example.hh.dto.response.GetProfileResponse;
import com.example.hh.dto.response.GetUserPostResponse;
import com.example.hh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{userName}/posts") @PreAuthorize("isAuthenticated()")
    public List<GetUserPostResponse> getUserPost(@PathVariable("userName")GetUserPostRequest request){return userService.responses(request);}

    @GetMapping("/{userId}/profile") @PreAuthorize("isAuthenticated()")
    public GetProfileResponse profile(@PathVariable GetProfileRequest getProfileRequest){return userService.getProfileResponse(getProfileRequest);}

    @PutMapping("/{userId}/profile") @PreAuthorize("isAuthenticated()")
    public void updateProfile(@PathVariable("userId")UpdateProfileRequest updateProfileRequest ,@RequestBody UpdateProfileBodyRequest update){userService.updateProfile(updateProfileRequest,update);}

}