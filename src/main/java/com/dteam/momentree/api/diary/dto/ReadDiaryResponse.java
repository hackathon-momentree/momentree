package com.dteam.momentree.api.diary.dto;

import com.dteam.momentree.domain.diary.OpenStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadDiaryResponse {

    private String content;
    private OpenStatus openStatus;
    private int day;

}
