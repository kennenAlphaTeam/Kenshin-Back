package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.mihoyo.MihoyoUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MihoyoApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Headers headers = chain.request().headers();
        Request request = chain.request().newBuilder()
                .headers(headers)
                .addHeader("x-rpc-language", "ko-kr")
                .addHeader("x-rpc-client_type", "4")
                .addHeader("x-rpc-app_version", "1.5.0")
                .addHeader("DS", MihoyoUtils.generateDSToken())
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}