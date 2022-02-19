package com.kennenalphateam.genshin.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다."),
    MIHOYO_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String detail;
}
