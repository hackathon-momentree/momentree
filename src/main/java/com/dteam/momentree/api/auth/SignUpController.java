package com.dteam.momentree.api.auth;

import com.dteam.momentree.api.auth.dto.SignUpRequest;
import com.dteam.momentree.application.auth.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "회원가입 API",
    description = """
            - **loginId**는 가입할 ID명입니다.
            - **password**에 비밀번호를 작성하시면 됩니다.
            """)
    public Long signUp(@RequestBody @Valid SignUpRequest request){
        return signUpService.signUp(request);
    }
}
