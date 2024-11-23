package com.dteam.momentree.domain.user;

import com.dteam.momentree.domain.UserBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Present{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // react 당하는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @Column(length = 10, nullable = false)
    private String reactNickname;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate createDate;
}
