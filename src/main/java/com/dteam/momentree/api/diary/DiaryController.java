package com.dteam.momentree.api.diary;

import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.api.diary.dto.ReadDiaryResponse;
import com.dteam.momentree.application.config.auth.Auth;
import com.dteam.momentree.application.config.auth.LoginUser;
import com.dteam.momentree.application.diary.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
    @Operation(summary = "일기 생성 API", description = "특정 날짜에 대한 일기를 생성합니다.")
    public ResponseEntity<DiaryResponse> createDiary(
            @RequestBody @Valid DiaryRequest request,
            @Parameter(description = "일기를 생성할 날짜 (1~31)", example = "1")
            @PathVariable int day,
            @Parameter(hidden = true) @Auth LoginUser loginUser) {

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

    @GetMapping("/unassigned")
    @Operation(summary = "트리에 배치가 안된 이모티콘(일기) 조회 API", description = "트리 위치에 배치되지 않은 일기(이모티콘)를 조회합니다.")
    public ResponseEntity<List<ReadDiaryResponse>> getUnassignedDiaries(@Auth LoginUser loginUser) {
        List<ReadDiaryResponse> response = diaryService.getDiariesWithoutLocation(loginUser.getUserId());
        return ResponseEntity.ok(response);
    }
}