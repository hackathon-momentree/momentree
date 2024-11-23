package com.dteam.momentree.api.diary;

import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.api.diary.dto.ReadDiaryResponse;
import com.dteam.momentree.application.diary.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dairy")
public class DiaryController {

    private final DiaryService diaryService;


    @PostMapping("/create/{day}")
    @Operation(summary = "일기 생성 API")
    public ResponseEntity<DiaryResponse> createDiary(@RequestBody @Valid DiaryRequest request,
                                                     @PathVariable int day) {

        DiaryResponse response = diaryService.createDiary(request, day);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-day/{day}")
    @Operation(summary = "일 단위 일기 조회 API")
    public ResponseEntity<ReadDiaryResponse> getDiaryByDay(@PathVariable int day) {
        ReadDiaryResponse response = diaryService.getDiaryByDay(day);
        return ResponseEntity.ok(response);
    }


}