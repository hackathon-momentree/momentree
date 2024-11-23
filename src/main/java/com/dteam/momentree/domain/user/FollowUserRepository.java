package com.dteam.momentree.domain.user;

import com.dteam.momentree.api.UserFollow.UserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowUserRepository extends JpaRepository<Following, Long> {
    boolean existsByFollowingUserAndFollowedUser(User followingUser, User followedUser);
    @Query("SELECT f FROM Following f WHERE f.followingUser = :followingUser")
    List<Following> findAllByFollowingUser(@Param("followingUser") User followingUser);

}
