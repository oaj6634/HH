package com.example.hh.repository;

import com.example.hh.domain.Post;
import com.example.hh.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long > {

    Page<Post> findByUserId(User user, Pageable pageable);
}
