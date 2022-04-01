package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;
import com.kennenalphateam.genshin.mihoyo.MihoyoUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Component
public class MihoyoRequestInterceptor implements RequestInterceptor {

    @Value("${mihoyo.rpc-client_type}")
    private String clientType;

    @Value("${mihoyo.rpc-app_version}")
    private String appVersion;

    @Value("${mihoyo.rpc-language}")
    private String language;

    @Override
    public void apply(RequestTemplate template) {
        template.header("x-rpc-language", language);
        template.header("x-rpc-client_type", clientType);
        template.header("x-rpc-app_version", appVersion);
        template.header("DS", MihoyoUtils.generateDSToken());

        String cookie = (String) RequestContextHolder.currentRequestAttributes().getAttribute(MihoyoUtils.REQUEST_CONTEXT_MIHOYO_COOKIE_KEY, RequestAttributes.SCOPE_REQUEST);
        if (cookie == null)
            throw new ErrorException(ErrorCode.UNREGISTERED_GENSHIN_USER);
        template.header("Cookie", cookie);
    }
}
