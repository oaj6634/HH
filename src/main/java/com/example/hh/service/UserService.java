package com.example.hh.service;

import com.example.hh.domain.Post;
import com.example.hh.domain.User;
import com.example.hh.dto.request.*;
import com.example.hh.dto.response.GetProfileResponse;
import com.example.hh.dto.response.GetUserPostResponse;
import com.example.hh.dto.response.LoginResponse;
import com.example.hh.dto.response.TokenRefreshResponse;
import com.example.hh.error.exception.ExistUserException;
import com.example.hh.error.exception.InvalidTokenException;
import com.example.hh.error.exception.PasswordNotMatchedException;
import com.example.hh.error.exception.UserNotFoundException;
import com.example.hh.repository.PostRepository;
import com.example.hh.repository.UserRepository;
import com.example.hh.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PostRepository postRepository;
    private final FileUploadService fileUploadService;

    public void join(JoinRequest join){
        String id = join.getUserId();

        if (userRepository.existsById(id)) {
            throw new ExistUserException(id);
        }

        User user = User.builder()
                .userId(id)
                .password(passwordEncoder.encode(join.getPassword()))
                .email(join.getEmail())
                .userName(join.getUserName())
                .zipCode(join.getZipCode())
                .build();
        userRepository.save(user);
    }
    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.getUserId()));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new PasswordNotMatchedException(loginRequest.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUserId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserId());

         return LoginResponse.builder()
                 .accessToken(accessToken)
                 .refreshToken(refreshToken)
                 .userId(user.getUserId())
                 .password(user.getPassword())
                 .email(user.getEmail())
                 .userName(user.getUserName())
                 .zipCode(user.getZipCode())
                 .build();
    }
    public TokenRefreshResponse tokenRefresh(String refreshToken){
        if(!jwtTokenProvider.validateRefreshToken(refreshToken)){
            throw new InvalidTokenException(refreshToken);
        }
        String id = jwtTokenProvider.getId(refreshToken);
        String accessToken = jwtTokenProvider.generateAccessToken(id);
        return new TokenRefreshResponse(accessToken);
    }
    public List<GetUserPostResponse> responses(Pageable pageable){
        User user = authService.getUser();
        Page<Post> posts = postRepository.findByUser(user.getUserId(), pageable);
        List<GetUserPostResponse> getUser = new ArrayList<>();

        for(Post post : posts){
            GetUserPostResponse getUserPost = GetUserPostResponse.builder()
                    .content(post.getContent())
                    .title(post.getTitle())
                    .date(post.getCreateAt())
                    .imageUrl(post.getImageUrl())
                    .build();

            getUser.add(getUserPost);
        }

        return getUser;
    }
    public GetProfileResponse getProfileResponse(){
        User user = userRepository.findByUserId(authService.getUser().getUserId())
                .orElseThrow(()-> new UserNotFoundException(authService.getUser().getUserId()));

        if(user.getDescription() == null && user.getImageUrl() == null){
            GetProfileResponse getProfileResponse = GetProfileResponse.builder()
                    .email(user.getEmail())
                    .imageUrl("")
                    .description("")
                    .userName(user.getUserName())
                    .build();

            return getProfileResponse;
        }

        GetProfileResponse getProfile = GetProfileResponse.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .imageUrl(user.getImageUrl())
                .build();

        return getProfile;
    }
    public void updateProfile(MultipartFile image, UpdateProfileBodyRequest description, UpdateProfileBodyRequest userName){
        User user = userRepository.findByUserId(authService.getUser().getUserId())
                        .orElseThrow(()->new UserNotFoundException(authService.getUser().getUserId()));

        user.update(userName.getUserName(),
                description.getDescription(),
                fileUploadService.uploadImage(image));


        userRepository.save(user);
    }
}
