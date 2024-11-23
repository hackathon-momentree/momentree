package com.dteam.momentree.application.config.jwt;


import com.dteam.momentree.application.auth.AuthService;
import com.dteam.momentree.application.config.auth.UserContext;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            String token =  jwtManager.resolveTokenFromRequest(request);
            checkTokenValid(token);

            Long userId = jwtManager.getUserIdFromRequest(request);
            UserContext.setCurrentUser(userId);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("JWT Auth fail. ",e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token.");
        }
    }

    public void checkTokenValid(String token){
        if(!jwtManager.isTokenValid(token)){
            throw new BadRequestException(ExceptionType.TOKEN_NOT_VALID);
        }
    }
}
