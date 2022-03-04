package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.mihoyo.MihoyoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MihoyoApiInterceptor implements Interceptor {

    @Value("${mihoyo.rpc-client_type}")
    private String clientType;

    @Value("${mihoyo.rpc-app_version}")
    private String appVersion;

    @Value("${mihoyo.rpc-language}")
    private String language;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Headers headers = chain.request().headers();
        Request request = chain.request().newBuilder()
                .headers(headers)
                .addHeader("x-rpc-language", language)
                .addHeader("x-rpc-client_type", clientType)
                .addHeader("x-rpc-app_version", appVersion)
                .addHeader("DS", MihoyoUtils.generateDSToken())
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}