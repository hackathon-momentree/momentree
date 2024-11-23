package com.dteam.momentree.api.Present.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PresentResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PresentResultDTO {
        private Long id;                // Present 엔티티의 고유 ID
        private LocalDate createdAt; // Present 엔티티 생성 시간
    }
}
