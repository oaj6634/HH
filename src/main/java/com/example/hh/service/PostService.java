package com.example.hh.service;

import com.example.hh.domain.Post;
import com.example.hh.dto.request.GetUserPostRequest;
import com.example.hh.dto.response.GetPostResponse;
import com.example.hh.dto.request.PostRequest;
import com.example.hh.dto.response.GetUserPostResponse;
import com.example.hh.error.exception.PostNotFoundException;
import com.example.hh.repository.PostRepository;
import com.example.hh.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    public List<GetPostResponse> getPost(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        List<GetPostResponse> getPost = new ArrayList<>();

        if (posts.isEmpty()){
            throw new PostNotFoundException();
        }

        for(Post post : posts) {
            GetPostResponse postRequest = GetPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .date(post.getDate())
                .build();

            getPost.add(postRequest);
        }
        return getPost;
    }

    public void post(PostRequest postRequest){

        Post post = Post.builder()
                .content(postRequest.getStrContent())
                .date(LocalDateTime.now())
                .title(postRequest.getStrTitle())
                .userId(authService.getUser())
                .build();

        postRepository.save(post);
    }

}
