package com.dteam.momentree.application.config.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    UNKNOWN_ERROR("유효하지 않은 요청입니다.",400),
    USER_NOT_VALID("유효하지 않은 사용자입니다.", 401),
    TOKEN_NOT_EXIST("요청에 토큰이 포함되지 않았습니다.", 402),
    TOKEN_NOT_VALID("토큰이 유효하지 않습니다.", 403),
    DUPLICATED_USER("이미 가입된 회원입니다.", 409),
    ALREADY_FOLLOWED_USER("이미 팔로우한 회원입니다.", 409),
    INVALID_INPUT("유효하지 않는 입력입니다. ", 400),
    CANNOT_FOLLOW_SELF("자기 자신을 팔로우할 수 없습니다.", 400),
    USER_NOT_FOUND("존재하지 않는 유저입니다.",404),
    DIARY_NOT_FOUND("존재하지 않는 일기입니다", 404),
    DAY_NOT_FOUND("날짜 범위를 벗어났습니다", 400),
    DAY_ALREADY_EXISTS("이미 존재하는 day 값입니다.", 409),
    INVALID_LOCATION("유효하지 않은 위치입니다", 404),
    FORBIDDEN("권한이 없습니다.", 403),
    LOCATION_ALREADY_USED("해당 위치는 이미 사용 중입니다", 409),
    NO_DIARY_IN_LOCATION("해당 영역에는 일기가 없습니다.", 404);

    private final String message;
    private final int code;

    ExceptionType(String message, int code){
        this.message = message;
        this.code = code;
    }
}
