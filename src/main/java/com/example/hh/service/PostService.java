package com.example.hh.service;

import com.example.hh.dto.request.PostRequest;
import com.example.hh.dto.response.PostResponse;
import com.example.hh.repository.PostRepository;
import com.example.hh.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public PostResponse getPost(PostRequest postRequest){

    }
}
