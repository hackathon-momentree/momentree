package com.dteam.momentree.application.auth;

import com.dteam.momentree.api.auth.dto.SignInRequest;
import com.dteam.momentree.api.auth.dto.SignInResponse;
import com.dteam.momentree.application.UserService;
import com.dteam.momentree.application.util.PasswordUtil;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.application.config.jwt.JwtManager;
import com.dteam.momentree.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtManager jwtManager;

    public SignInResponse signIn(SignInRequest request){
        Optional<User> user = userService.findByLoginId(request.loginId());
        if(user.isEmpty() || !PasswordUtil.matches(request.password(), user.get().getPassword())){
            throw new BadRequestException(ExceptionType.USER_NOT_VALID);
        }
        String token = jwtManager.generateAccessToken(user.get().getId());
        return new SignInResponse(token, user.get().getId());
    }

}
