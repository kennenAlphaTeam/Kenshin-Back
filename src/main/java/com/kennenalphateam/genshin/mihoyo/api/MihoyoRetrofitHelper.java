package com.kennenalphateam.genshin.mihoyo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class MihoyoRetrofitHelper {

    private final ObjectMapper objectMapper;

    public <T> T requestBody(Call<MihoyoResponse> call, Class<T> dataType) {
        Response<MihoyoResponse> response = executeCall(call);

        checkResponseSuccessful(response);
        return getDataFromResponse(response.body(), dataType);
    }

    private <T> T getDataFromResponse(MihoyoResponse response, Class<T> dataType) {
        ObjectNode data = response.getData();

        try {
            if (dataType == String.class)
                return (T) objectMapper.writeValueAsString(data);
            return objectMapper.convertValue(data, dataType);
        } catch (JsonProcessingException e) {
            log.error(response.getMessage());
            e.printStackTrace();
            throw  new RuntimeException("");
        }
    }

    private Response<MihoyoResponse> executeCall(Call<MihoyoResponse> call) {
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    public void checkResponseSuccessful(Response<MihoyoResponse> response) {
        if (!response.isSuccessful() || response.body() == null || response.body().getRetcode() != 0)
            throw new RuntimeException("");
    }
}
