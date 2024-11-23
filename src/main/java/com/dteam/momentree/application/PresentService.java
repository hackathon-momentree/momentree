package com.dteam.momentree.application;

import com.dteam.momentree.api.Present.dto.PresentRequest;
import com.dteam.momentree.api.Present.dto.PresentResponse;
import com.dteam.momentree.api.UserFollow.UserResponseDTO;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentService {
    private final PresentRepository presentRepository;
    private final UserRepository userRepository;

    //방명록 생성
    @Transactional
    public PresentResponse.PresentResultDTO save(PresentRequest.PresentDTO presentDTO, User ownerUser){
        if (presentDTO == null) {
            log.error("Invalid request: PresentDTO is null.");
            throw new BadRequestException(ExceptionType.INVALID_INPUT);
        }

        if (ownerUser == null) {
            log.error("Invalid request: OwnerUser is null.");
            throw new BadRequestException(ExceptionType.USER_NOT_FOUND);
        }
        log.info("Saving present: content={}, reactNickname={}, ownerUserId={}",
                presentDTO.getContent(), presentDTO.getNickname(), ownerUser.getId());

        try {
            // 빌더 패턴을 사용하여 Present 객체 생성
            Present present = Present.builder()
                    .content(presentDTO.getContent())
                    .reactNickname(presentDTO.getNickname())
                    .ownerUser(ownerUser)
                    .createDate(presentDTO.getCreateTime())
                    .build();

            log.info("Present 빌더가 정상적으로 작동했습니다.");

            Present newPresent = presentRepository.save(present);

            log.info("Present saved successfully: id={}, createdDate={}", newPresent.getId(), newPresent.getCreateDate());

            // 결과 DTO 생성 및 반환
            return PresentResponse.PresentResultDTO.builder()
                    .id(newPresent.getId())
                    .createdAt(newPresent.getCreateDate())
                    .build();
        } catch (Exception e) {
            log.error("Unexpected error occurred while saving present: {}", e.getMessage(), e);
            throw new BadRequestException(ExceptionType.UNKNOWN_ERROR);
        }
    }

    //방명록 조회
    @Transactional(readOnly = true)
    public List<Present> getPresents(Long loginUserId) {
        // 로그인 사용자 확인
        User ownerUser = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));

        log.info("Owner user found: id={}, loginId={}", ownerUser.getId(), ownerUser.getLoginId());

        try {
            // 엔티티 조회
            List<Present> presents = presentRepository.findAllByOwnerUser(ownerUser);

            if (presents.isEmpty()) {
                log.warn("No presents found for ownerUserId={}", ownerUser.getId());
            } else {
                log.info("{} presents found for ownerUserId={}", presents.size(), ownerUser.getId());
            }

            // DTO로 변환
            return presents.stream()
                    .map(present -> Present.builder()
                            .id(present.getId())
                            .content(present.getContent())
                            .reactNickname(present.getReactNickname())
                            .createDate(present.getCreateDate())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Unexpected error occurred while retrieving presents: {}", e.getMessage(), e);
            throw new BadRequestException(ExceptionType.UNKNOWN_ERROR);
        }
    }

}
