package com.dteam.momentree.application;

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

    public Long save(User user){
        userRepository.save(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public Optional<User> findByLoginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }
}
