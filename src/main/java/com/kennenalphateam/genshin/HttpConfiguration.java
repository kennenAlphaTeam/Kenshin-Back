package com.kennenalphateam.genshin;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class HttpConfiguration implements WebMvcConfigurer {

    private final HttpLogInterceptor httpLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpLogInterceptor)
                .addPathPatterns("/**");
    }
}
