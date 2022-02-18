package com.kennenalphateam.genshin.error;

import com.kennenalphateam.genshin.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("Unknown exception {} : {} {} {}", e.getClass().getName(), IpUtils.getIpFromRequest(request), request.getRequestURI(), e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ErrorException.class})
    public ResponseEntity<ErrorResponse> handleErrorException(ErrorException e, HttpServletRequest request) {
        log.error("ErrorException {} : {} {} [{}] {}", e.getClass().getName(), IpUtils.getIpFromRequest(request), request.getRequestURI(), e.getErrorCode().getHttpStatus().value(), e.getErrorCode().getDetail());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
