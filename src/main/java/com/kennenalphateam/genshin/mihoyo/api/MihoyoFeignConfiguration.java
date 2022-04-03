package com.kennenalphateam.genshin.mihoyo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class MihoyoFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new MihoyoRequestInterceptor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .build();
    }

    @Bean
    public Decoder decoder() {
        return new MihoyoApiDecoder(objectMapper());
    }
}
