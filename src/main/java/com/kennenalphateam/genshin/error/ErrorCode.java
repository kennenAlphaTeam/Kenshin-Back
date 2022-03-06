package com.kennenalphateam.genshin.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다."),

    MIHOYO_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_COOKIE(HttpStatus.BAD_REQUEST, "잘못된 쿠키입니다."),
    INVALID_GENSHIN_UID(HttpStatus.BAD_REQUEST, "잘못된 UID입니다."),
    UNREGISTERED_GENSHIN_USER(HttpStatus.UNAUTHORIZED, "Cookie를 등록하지 않은 유저입니다."),

    USER_UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "인증되지 않은 유저입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String detail;
}
