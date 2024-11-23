package com.dteam.momentree.domain.user;

import com.dteam.momentree.domain.UserBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Following{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팔로우 당하는 사용자 (Followed)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_user_id", nullable = false) // 컬럼명 수정
    private User followedUser;

    // 팔로우 하는 사용자 (Following)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id", nullable = false) // 컬럼명 수정
    private User followingUser;
}
