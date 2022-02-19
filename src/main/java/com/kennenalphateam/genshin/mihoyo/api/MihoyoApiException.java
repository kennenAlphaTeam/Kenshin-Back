package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;

public class MihoyoApiException extends ErrorException {
    public MihoyoApiException() {
        super(ErrorCode.MIHOYO_REQUEST_ERROR);
    }
}
