package com.dteam.momentree.api;

import com.dteam.momentree.api.test.TestController;
import com.dteam.momentree.application.UserService;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class UserFollowController{
    private final UserService userService;

    @GetMapping("/{followedUserLoginId}")
    @Operation(summary = "followUser API", description = "팔로우할 유저를 찾아 팔로우를 하는 API이고, query String 으로 page 번호를 주세요")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, 성공"),
            @ApiResponse(responseCode = "401", description = "access 토큰 오류 (토큰이 없거나 유효하지 않음)", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "410", description = "존재하지 않는 유저입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public String followUser(
            @PathVariable(name = "followedUserLoginId") String followedUserLoginId,  @Auth LoginUser loginUser) {
        try {
            // 디버깅 로그: loginUser와 followedUserLoginId 확인
            log.info("followedUserLoginId: {}", followedUserLoginId);
            log.info("loginUser: {}", loginUser);

            // 로그인 사용자 ID는 loginUser에서 자동으로 가져옴
            Long loginUserId = loginUser.getUserId();
            log.info("loginUserId: {}", loginUserId);

            // 팔로우할 사용자 조회 및 처리
            User followedUser = userService.followUser(followedUserLoginId, loginUserId);
            log.info("followedUser: {}", followedUser);

            if (followedUser == null) {
                throw new BadRequestException(ExceptionType.USER_NOT_FOUND);
            }

            return followedUser.getLoginId();
        } catch (BadRequestException e) {
            log.error("BadRequestException occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            throw new BadRequestException(ExceptionType.UNKNOWN_ERROR);
        }
    }
}
