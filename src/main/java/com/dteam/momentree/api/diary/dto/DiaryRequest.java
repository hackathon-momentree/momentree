package com.dteam.momentree.api.diary.dto;

import com.dteam.momentree.domain.diary.OpenStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class DiaryRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private OpenStatus openStatus;

    private List<String> dairyImageUrls;
}
