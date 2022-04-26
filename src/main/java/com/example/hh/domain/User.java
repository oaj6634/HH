package com.example.hh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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
    @Column(name = "user_id", length = 100)
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String userName;

    @Column(name = "zip_code")
    private Long zipCode;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public void update(String password, String userName, Long zipCode){
        this.password = password;
        this.userName = userName;
        this.zipCode = zipCode;

    }


}
