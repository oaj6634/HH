package com.example.hh.service;

import com.example.hh.domain.Post;
import com.example.hh.domain.User;
import com.example.hh.dto.request.PostRequest;
import com.example.hh.dto.response.PostResponse;
import com.example.hh.repository.PostRepository;
import com.example.hh.security.JwtTokenProvider;
import com.example.hh.security.details.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    public void post(PostRequest postRequest){

        Post post = Post.builder()
                .content(postRequest.getContent())
                .date(LocalDateTime.now())
                .title(postRequest.getTitle())
                .userId(authService.getUser())
                .build();

        postRepository.save(post);
    }

}
