package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import com.kennenalphateam.genshin.mihoyo.dto.CharacterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MihoyoService {
    private final MihoyoApi mihoyoApi;
    private final MihoyoRetrofitHelper helper;

    public String getProfile(String genshinUid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);

        return helper.requestBody(mihoyoApi.getProfile(cookie, server, genshinUid), String.class);
    }

    public String getDailyNote(String genshinUid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);

        return helper.requestBody(mihoyoApi.getDailyNote(cookie, server, genshinUid), String.class);
    }

    public String getCharacterInfoList(String genshinUid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);
        CharacterRequest body = CharacterRequest.builder()
                .characterIds(new int[]{10000021})
                .server(server)
                .genshinUid(genshinUid)
                .build();
        return helper.requestBody(mihoyoApi.getCharacterInfoList(cookie, body), String.class);
    }
}
