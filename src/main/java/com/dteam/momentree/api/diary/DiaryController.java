package com.dteam.momentree.api.diary;

import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.api.diary.dto.DiaryUpdateRequest;
import com.dteam.momentree.api.diary.dto.ReadDiaryResponse;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.application.diary.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/create/{day}")
    @Operation(summary = "일기 생성 API",
            description = """
                특정 날짜에 대한 일기를 작성하는 API입니다.
                - **content**는 일기 내용을 나타냅니다.
                - **OpenStatus**값에 OPEN은 공개상태, CLOSED는 비공개상태입니다.
                """)
    public ResponseEntity<DiaryResponse> createDiary(
            @RequestBody @Valid DiaryRequest request,
            @Parameter(description = "일기를 생성할 날짜 (1~31)", example = "1")
            @PathVariable int day,
            @Auth LoginUser loginUser) {

        DiaryResponse response = diaryService.createDiary(request, day, loginUser.getUserId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-day/{day}")
    @Operation(summary = "일 단위 일기 조회 API", description = "특정 날짜에 작성된 일기를 조회합니다.")
    public ResponseEntity<ReadDiaryResponse> getDiaryByDay(
            @Parameter(description = "조회할 날짜 (1~31)", example = "5")
            @PathVariable int day,
            @Auth LoginUser loginUser) {

        ReadDiaryResponse response = diaryService.getDiaryByDay(loginUser.getUserId(), day);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{day}/assign-location/{location}")
    @Operation(summary = "특정 위치에 회원별 일기 배치 API")
    public ResponseEntity<Void> assignLocationToDiary(
            @Parameter(description = "배치할 일기의 날짜 (1~31)", example = "1")
            @PathVariable int day,
            @Parameter(description = "트리에 배치할 영역 (1~18)", example = "1")
            @PathVariable Long location,
            @Auth LoginUser loginUser) {
        diaryService.assignLocationToDiary(day, location, loginUser.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/unassign-location/{location}")
    @Operation(summary = "특정 위치의 회원별 일기 해제 API")
    public ResponseEntity<Void> unassignLocation(
            @Parameter(description = "트리에 배치를 해체할 영역 (1~18)", example = "1")
            @PathVariable Long location,
            @Auth LoginUser loginUser) {
        diaryService.unassignLocation(location, loginUser.getUserId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unassigned")
    @Operation(summary = "트리에 배치가 안된 이모티콘(일기) 조회 API", description = "트리 위치에 배치되지 않은 일기(이모티콘)를 조회합니다.")
    public ResponseEntity<List<ReadDiaryResponse>> getUnassignedDiaries(@Auth LoginUser loginUser) {
        List<ReadDiaryResponse> response = diaryService.getDiariesWithoutLocation(loginUser.getUserId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    @Operation(summary = "일기의 내용 수정 API", description = """
            일기 내용을 수정하는 API 입니다.
            - **id**는 해당 일기의 고유 ID값입니다.
            - **content**는 일기의 수정할 내용을 적는 곳입니다.
            """)
    public void update(@Auth LoginUser user, @RequestBody DiaryUpdateRequest updateRequest) {
        diaryService.update(user.getUserId(), updateRequest);
    }


}