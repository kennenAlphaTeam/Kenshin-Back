package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MihoyoService {
    private final MihoyoApi mihoyoApi;
    private final MihoyoRetrofitHelper helper;

    public String getProfile(String uid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(uid);

        return helper.requestBody(mihoyoApi.getProfile(server, uid, cookie), String.class);
    }

    public String getDailyNote(String uid, String cookie) {
        String server = MihoyoUtils.getServerNameFromUid(uid);

        return helper.requestBody(mihoyoApi.getDailyNote(server, uid, cookie), String.class);
    }
}
