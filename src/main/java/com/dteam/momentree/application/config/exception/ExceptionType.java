package com.dteam.momentree.application.config.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    UNKNOWN_ERROR("유효하지 않은 요청입니다.",400),
    USER_NOT_VALID("유효하지 않은 사용자입니다.", 401),
    TOKEN_NOT_EXIST("요청에 토큰이 포함되지 않았습니다.", 402),
    TOKEN_NOT_VALID("토큰이 유효하지 않습니다.", 403),
    DUPLICATED_USER("이미 가입된 회원입니다.", 409),
    ALREADY_FOLLOWED_USER("이미 팔로우한 회원입니다.", 411),
    USER_NOT_FOUND("존재하지 않는 유저입니다.",410),
    DIARY_NOT_FOUND("존재하지 않는 일기입니다", 412),
    DAY_NOT_FOUND("날짜 범위를 벗어났습니다", 413),
    DAY_ALREADY_EXISTS("이미 존재하는 day 값입니다.", 414);

    private final String message;
    private final int code;

    ExceptionType(String message, int code){
        this.message = message;
        this.code = code;
    }
}
