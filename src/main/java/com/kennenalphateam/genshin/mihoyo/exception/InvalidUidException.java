package com.kennenalphateam.genshin.mihoyo.exception;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;

public class InvalidUidException extends ErrorException {
    public InvalidUidException() {
        super(ErrorCode.INVALID_GENSHIN_UID);
    }
}
