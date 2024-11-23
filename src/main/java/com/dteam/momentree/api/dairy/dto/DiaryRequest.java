package com.dteam.momentree.api.dairy.dto;

import com.dteam.momentree.domain.dairy.OpenStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class DiaryRequest {

    private String title;

    private String content;

    private OpenStatus openStatus;

    private List<String> dairyImageUrls;
}
