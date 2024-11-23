package com.dteam.momentree.api.Present;

import com.dteam.momentree.api.Present.dto.PresentRequest;
import com.dteam.momentree.api.Present.dto.PresentResponse;
import com.dteam.momentree.api.UserFollow.UserResponseDTO;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.application.PresentService;
import com.dteam.momentree.application.UserService;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.user.Present;
import com.dteam.momentree.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/present")
public class PresentController {
    private final PresentService presentService;
    private final UserService userService;

    @PostMapping("/{ownerId}")
    @Operation(
            summary = "방명록 작성 API",
            description = """
                방명록을 작성하는 API입니다. 
                - **ownerId**는 방명록 소유자의 login ID(String)을 나타냅니다.
                - 요청 본문에는 방명록 내용과 작성자 닉네임, 작성한 날짜가 포함되어야 합니다.
                """
    )
    public ResponseEntity<PresentResponse.PresentResultDTO> writePresent(
            @RequestBody PresentRequest.PresentDTO presentDTO,
            @PathVariable(name = "ownerId") String ownerId) {
        // ownerId를 통해 User 객체를 조회
        User owner = userService.findByLoginId(ownerId)
                .orElseThrow(() -> new BadRequestException(ExceptionType.USER_NOT_FOUND));

        // Present 저장 및 결과 생성
        PresentResponse.PresentResultDTO response = presentService.save(presentDTO, owner);

        // 결과 반환
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    @Operation(
            summary = "내가 받은 방명록 조회 API",
            description = """
                현재 로그인된 사용자가 받은 방명록을 조회하는 API입니다.
                - **로그인 사용자 ID**를 기반으로 소유자의 방명록을 가져옵니다.
                """
    )
    public List<Present> PresentsList(@Auth LoginUser loginUser){
        Long loginUserId = loginUser.getUserId();
        return presentService.getPresents(loginUserId);
    }

}
