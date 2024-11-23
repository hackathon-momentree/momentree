package com.dteam.momentree.api.diary.dto;

import com.dteam.momentree.domain.diary.OpenStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DiaryRequest {

    @NotNull
    private String content;

    @NotNull
    private OpenStatus openStatus;

}
