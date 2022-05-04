package com.example.hh.repository;

import com.example.hh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByUserId(String userId);

}
