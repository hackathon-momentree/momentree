package com.dteam.momentree.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String loginId;
    @Column(nullable = false)
    private String password;

    public static User of(String loginId, String password){
        User user = new User();
        user.loginId = loginId;
        user.password = password;
        return user;
    }
}
