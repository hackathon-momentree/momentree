package com.dteam.momentree.api.auth;

import com.dteam.momentree.api.auth.dto.SignInRequest;
import com.dteam.momentree.api.auth.dto.SignInResponse;
import com.dteam.momentree.application.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @Operation(summary = "로그인 API",
            description = """
            - **loginId**는 가입한 ID명입니다.
            - **password**에 비밀번호를 작성하시면 됩니다.
            """)
    public SignInResponse signIn(@RequestBody SignInRequest request){
        return authService.signIn(request);
    }

}
