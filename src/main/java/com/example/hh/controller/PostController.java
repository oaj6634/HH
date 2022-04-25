package com.example.hh.controller;

import com.example.hh.domain.Post;
import com.example.hh.dto.response.GetPostResponse;
import com.example.hh.dto.request.PostRequest;
import com.example.hh.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts") @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<GetPostResponse>> getPost(Pageable pageable){
        return new ResponseEntity<>(postService.getPost(pageable), HttpStatus.OK);
    }

    @PostMapping("/posts") @PreAuthorize("isAuthenticated()")
    @ResponseStatus(value = HttpStatus.OK)
    public void post(@RequestPart PostRequest postRequest){
        postService.post(postRequest);
    }

}
