package com.dteam.momentree.api.auth;

import com.dteam.momentree.api.auth.dto.SignInRequest;
import com.dteam.momentree.api.auth.dto.SignInResponse;
import com.dteam.momentree.application.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest request){
        return authService.signIn(request);
    }

}
