package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.dto.CharacterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MihoyoService {
    private final MihoyoApi mihoyoApi;

    public String getProfile(String genshinUid) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);

        return mihoyoApi.getProfile(server, genshinUid);
    }

    public String getDailyNote(String genshinUid) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);

        return mihoyoApi.getDailyNote(server, genshinUid);
    }

    public String getCharacterInfoList(String genshinUid) {
        String server = MihoyoUtils.getServerNameFromUid(genshinUid);
        CharacterRequest body = CharacterRequest.builder()
                .characterIds(new int[]{10000021})
                .server(server)
                .genshinUid(genshinUid)
                .build();
        return mihoyoApi.getCharacterInfoList(body);
    }
}
