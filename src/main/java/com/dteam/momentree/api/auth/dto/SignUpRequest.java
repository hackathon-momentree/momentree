package com.dteam.momentree.api.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @NotNull
        String loginId,
        @NotNull
        String password
){
}
