package com.dteam.momentree.api.auth;

import com.dteam.momentree.api.auth.dto.SignUpRequest;
import com.dteam.momentree.application.auth.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public Long signUp(@RequestBody @Valid SignUpRequest request){
        return signUpService.signUp(request);
    }
}
