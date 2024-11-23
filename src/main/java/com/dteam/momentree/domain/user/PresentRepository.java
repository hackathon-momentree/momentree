package com.dteam.momentree.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentRepository extends JpaRepository<Present, Long> {
    // OwnerUser로 Present를 조회하는 메서드
    List<Present> findAllByOwnerUser(User ownerUser);
}
