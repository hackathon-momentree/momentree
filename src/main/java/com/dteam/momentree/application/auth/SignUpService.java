package com.dteam.momentree.application.auth;

import com.dteam.momentree.api.auth.dto.SignUpRequest;
import com.dteam.momentree.application.UserService;
import com.dteam.momentree.application.util.PasswordUtil;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserService userService;

    public Long signUp(SignUpRequest request){
        checkRequestValidation(request);
        String encryptedPassword = PasswordUtil.encryptPassword(request.password());
        User user = User.of(request.loginId(), encryptedPassword);
        return userService.save(user);
    }

    private void checkRequestValidation(SignUpRequest request) {
        Optional<User> user = userService.findByLoginId(request.loginId());
        if(user.isPresent()) throw new BadRequestException(ExceptionType.DUPLICATED_USER);
    }
}
