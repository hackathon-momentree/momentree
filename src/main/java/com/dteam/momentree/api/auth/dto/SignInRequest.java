package com.dteam.momentree.api.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SignInRequest (
        @NotNull
        String loginId,
        @NotNull
        String password
){
}
