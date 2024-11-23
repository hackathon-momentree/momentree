package com.dteam.momentree.api.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryUpdateRequest {
    private Long id;
    private String content;
}
