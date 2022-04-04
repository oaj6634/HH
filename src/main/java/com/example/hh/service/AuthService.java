package com.example.hh.service;

import com.example.hh.domain.User;
import com.example.hh.security.details.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Component
public class AuthService {

    public User getUser(){
        AuthUserDetails principal = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();

        return user;
    }
}
