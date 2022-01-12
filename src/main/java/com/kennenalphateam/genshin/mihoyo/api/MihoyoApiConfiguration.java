package com.kennenalphateam.genshin.mihoyo.api;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor()
public class MihoyoApiConfiguration {

    final static int CONNECTION_TIMEOUT = 5000;
    final static int READ_TIMEOUT = 5000;
    private final Interceptor mihoyoApiInterceptor;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMillis(CONNECTION_TIMEOUT))
                .readTimeout(Duration.ofMillis(READ_TIMEOUT))
                .addInterceptor(mihoyoApiInterceptor)
                .build();
    }

    @Bean
    public Retrofit retrofit() {
        String baseUrl = "https://bbs-api-os.mihoyo.com";
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient())
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public MihoyoApi mihoyoApi(Retrofit retrofit) {
        return retrofit.create(MihoyoApi.class);
    }
}
