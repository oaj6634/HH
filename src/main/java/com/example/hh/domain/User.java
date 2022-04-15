package com.example.hh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "zip_code")
    private Long zipCode;

    @OneToMany(mappedBy = "userId")
    private List<Post> posts = new ArrayList<>();

    public void update(String password, String userName, Long zipCode){
        this.password = password;
        this.userName = userName;
        this.zipCode = zipCode;

    }


}
