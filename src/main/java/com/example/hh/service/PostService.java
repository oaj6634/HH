package com.example.hh.service;

import com.example.hh.domain.Post;
import com.example.hh.dto.request.PostRequestTitle;
import com.example.hh.dto.response.GetPostResponse;
import com.example.hh.dto.request.PostRequestContent;
import com.example.hh.repository.PostRepository;
import com.example.hh.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;
    private final FileUploadService fileUploadService;

    public List<GetPostResponse> getPost(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        List<GetPostResponse> getPost = new ArrayList<>();

        for(Post post : posts) {
            GetPostResponse postRequest = GetPostResponse.builder()
                    .title(post.getTitle())
                    .content(post.getContent())
                    .date(post.getCreateAt())
                    .imageUrl(post.getImageUrl())
                    .build();

            getPost.add(postRequest);
        }
        return getPost;
    }

    public void post(MultipartFile multipartFile, PostRequestContent postRequestContent, PostRequestTitle postRequestTitle) {

        Post post = Post.builder()
                .content(postRequestContent.getContent())
                .createAt(LocalDateTime.now())
                .title(postRequestTitle.getTitle())
                .user(authService.getUser())
                .imageUrl(fileUploadService.uploadImage(multipartFile))
                .build();

        postRepository.save(post);
    }
}
