package com.dteam.momentree.api.diary.dto;

import com.dteam.momentree.domain.diary.OpenStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DiaryRequest {

    @Schema(description = "일기 내용", example = "오늘의 일기 내용입니다.")
    @NotNull
    private String content;

    @Schema(description = "공개 상태 (OPEN: 모두공개, CLOSED: 비공개)", example = "OPEN")
    @NotNull
    private OpenStatus openStatus;

}
