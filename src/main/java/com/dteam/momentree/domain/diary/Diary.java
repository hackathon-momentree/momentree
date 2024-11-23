package com.dteam.momentree.domain.diary;

import com.dteam.momentree.domain.TimeBaseEntity;
import com.dteam.momentree.domain.UserBaseEntity;
import com.dteam.momentree.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary extends UserBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private Long location;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OpenStatus openStatus;

    @Column(nullable = false, unique = true)
    private int day;

    public void setLocation(Long location) {
        this.location = location;
    }
}
