package com.dteam.momentree.application;

import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.user.FollowUserRepository;
import com.dteam.momentree.domain.user.Following;
import com.dteam.momentree.domain.user.User;
import com.dteam.momentree.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowUserRepository followUserRepository;

    public Long save(User user){
        userRepository.save(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public Optional<User> findByLoginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }

    //follow하기
    @Transactional
    public User followUser(String followedUserLoginId, Long loginUserId) {
        // 1. 팔로우 대상 찾기
        User followedUser = findByLoginId(followedUserLoginId)
                .orElseThrow(() -> new BadRequestException(ExceptionType.USER_NOT_FOUND));

        // 2. 팔로우 요청자 (현재 로그인한 사용자) 찾기
        User followingUser = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));

        // 3. 중복 팔로우 방지
        if (followUserRepository.existsByFollowingUserAndFollowedUser(followingUser, followedUser)) {
            throw new BadRequestException(ExceptionType.ALREADY_FOLLOWED_USER);
        }

        // 4. 팔로우 관계 생성
        Following following = new Following();
        following.setFollowedUser(followedUser);
        following.setFollowingUser(followingUser);

        // 5. 팔로우 관계 저장
        followUserRepository.save(following);

        // 6. 팔로우한 사용자 반환
        return followedUser;
    }

    //내가 팔로우하는 사람들 목록 보기


}
