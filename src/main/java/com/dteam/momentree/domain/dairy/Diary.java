package com.dteam.momentree.domain.dairy;

import com.dteam.momentree.domain.UserBaseEntity;
import com.dteam.momentree.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Diary extends UserBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long location;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OpenStatus openStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
