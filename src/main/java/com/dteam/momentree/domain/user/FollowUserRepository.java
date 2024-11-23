package com.dteam.momentree.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowUserRepository extends JpaRepository<Following, Long> {
    boolean existsByFollowingUserAndFollowedUser(User followingUser, User followedUser);
}
