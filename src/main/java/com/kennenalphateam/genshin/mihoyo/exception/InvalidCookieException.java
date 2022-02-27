package com.kennenalphateam.genshin.mihoyo.exception;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;

public class InvalidCookieException extends ErrorException {
    public InvalidCookieException() {
        super(ErrorCode.INVALID_COOKIE);
    }
}
