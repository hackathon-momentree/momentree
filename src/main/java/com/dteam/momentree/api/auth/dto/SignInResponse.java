package com.dteam.momentree.api.auth.dto;

public record SignInResponse (
        String accessToken,
        Long userId
){

}
