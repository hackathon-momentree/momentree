package com.dteam.momentree.api.UserFollow;

import com.dteam.momentree.application.UserService;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class UserFollowController{
    private final UserService userService;

    @GetMapping("/{followedUserLoginId}")
    @Operation(summary = "followUser API", description = "팔로우할 유저를 찾아 팔로우를 하는 API")
    public String followUser(
            @PathVariable(name = "followedUserLoginId") @Parameter(description = "팔로우하고싶은 유저의 string ID") String followedUserLoginId, @Auth LoginUser loginUser) {
        // 디버깅 로그: loginUser와 followedUserLoginId 확인
        log.info("followedUserLoginId: {}", followedUserLoginId);
        log.info("loginUser: {}", loginUser);

        // 로그인 사용자 ID는 loginUser에서 자동으로 가져옴
        Long loginUserId = loginUser.getUserId();
        log.info("loginUserId: {}", loginUserId);

        // 팔로우할 사용자 조회 및 처리
        User followedUser = userService.followUser(followedUserLoginId, loginUserId);
        log.info("followedUser: {}", followedUser);

        return followedUser.getLoginId();

    }

    @GetMapping("/list")
    @Operation(summary = "내가 팔로우하는 사람들 목록 API")
    public List<UserResponseDTO.UserFollowingListDTO> followUsersList(@Auth LoginUser loginUser){
        Long loginUserId = loginUser.getUserId();
        return userService.getFollowedUsers(loginUserId);
    }
}
