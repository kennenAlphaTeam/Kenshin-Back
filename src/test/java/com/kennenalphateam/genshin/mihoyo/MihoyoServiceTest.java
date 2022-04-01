package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MihoyoServiceTest {

    @Mock
    MihoyoApi api;
    @InjectMocks
    MihoyoService service;


    @Test
    void getProfile을_정상적으로_호출한다() {
        String responseMock = "success";
        String genshinUid = "819688319";

        when(api.getProfile(eq("os_asia"), eq(genshinUid))).thenReturn(responseMock);

        String result = service.getProfile(genshinUid);
        assertEquals(result, responseMock);
    }

    @Test
    void getDailyNote_정상적으로_호출한다() {
        String responseMock = "success";
        String genshinUid = "819688319";

        when(api.getDailyNote(eq("os_asia"), eq(genshinUid))).thenReturn(responseMock);

        String result = service.getDailyNote(genshinUid);
        assertEquals(result, responseMock);
    }

    @Test
    void getCharacterInfoList_정상적으로_호출한다() {
        String responseMock = "success";
        String genshinUid = "819688319";

        when(api.getCharacterInfoList(any())).thenReturn(responseMock);

        String result = service.getCharacterInfoList(genshinUid);
        assertEquals(result, responseMock);
    }
}