package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoResponse;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.mock.Calls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MihoyoServiceTest {

    @Mock
    MihoyoApi api;
    @Mock
    MihoyoRetrofitHelper helper;
    MihoyoService service;

    @BeforeEach
    void setUp() {
        service = new MihoyoService(api, helper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProfile을_정상적으로_호출한다() {
        Call<MihoyoResponse> callMock = Calls.response(new MihoyoResponse());
        String responseMock = "success";
        String genshinUid = "819688319";
        String cookie = "cookie";
        when(api.getProfile(anyString(), eq("os_asia"), eq(genshinUid))).thenReturn(callMock);
        when(helper.requestBody(callMock, String.class)).thenReturn(responseMock);

        String result = service.getProfile(genshinUid, cookie);
        assertEquals(result, responseMock);
    }

    @Test
    void getDailyNote_정상적으로_호출한다() {
        Call<MihoyoResponse> callMock = Calls.response(new MihoyoResponse());
        String responseMock = "success";
        String genshinUid = "819688319";
        String cookie = "cookie";
        when(api.getDailyNote(anyString(), eq("os_asia"), eq(genshinUid))).thenReturn(callMock);
        when(helper.requestBody(callMock, String.class)).thenReturn(responseMock);

        String result = service.getDailyNote(genshinUid, cookie);
        assertEquals(result, responseMock);
    }

    @Test
    void getCharacterInfoList_정상적으로_호출한다() {
        Call<MihoyoResponse> callMock = Calls.response(new MihoyoResponse());
        String responseMock = "success";
        String genshinUid = "819688319";
        String cookie = "cookie";

        when(api.getCharacterInfoList(eq(cookie), any())).thenReturn(callMock);
        when(helper.requestBody(callMock, String.class)).thenReturn(responseMock);

        String result = service.getCharacterInfoList(genshinUid, cookie);
        assertEquals(result, responseMock);
    }
}