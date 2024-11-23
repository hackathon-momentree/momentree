package com.dteam.momentree.api.Present.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;


public class PresentRequest {
    //content, nickname
    @Getter
    public static class PresentDTO{
        @NotBlank
        String content;
        @NotNull
        String nickname;
        LocalDate createTime;
//        @NotNull
//        String userId;

    }


}
