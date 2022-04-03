package com.kennenalphateam.genshin.mihoyo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;


@Slf4j
@RequiredArgsConstructor
public class MihoyoApiDecoder implements Decoder {
    private final ObjectMapper mapper;

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.status() / 100 != 2)
            throw new MihoyoApiException();

        MihoyoResponse mihoyoResponse = mapper.readValue(response.body().asInputStream(), MihoyoResponse.class);

        if (!mihoyoResponse.isSuccess())
            throw new MihoyoApiException();
        if (type == String.class)
            return mihoyoResponse.getData().toString();
        return mapper.convertValue(mihoyoResponse.getData(), (Class<?>) type);
    }
}
