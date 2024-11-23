package com.dteam.momentree.api.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponse {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
