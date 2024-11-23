package com.dteam.momentree.domain.dairy;

import com.dteam.momentree.domain.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class DiaryImage extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String diaryImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false)
    private Diary diary;

}
