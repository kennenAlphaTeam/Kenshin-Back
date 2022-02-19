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

        return helper.requestBody(mihoyoApi.getProfile(server, genshinUid, cookie), String.class);
    }

    public String getDailyNote(String genshinUid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);

        return helper.requestBody(mihoyoApi.getDailyNote(server, genshinUid, cookie), String.class);
    }

    public String getCharacterInfoList(String genshinUid, int[] characterIds, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);
        CharacterRequest body = CharacterRequest.builder()
                .characterIds(characterIds)
                .server(server)
                .genshinUid(genshinUid)
                .build();
        return helper.requestBody(mihoyoApi.getCharacterInfoList(body, cookie), String.class);
    }
}
