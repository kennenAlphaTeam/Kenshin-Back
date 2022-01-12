package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MihoyoController {

    private final MihoyoApi mihoyoApi;
    private final MihoyoRetrofitHelper helper;
    @Value("${mihoyo.default.cookie}")
    private String defaultCookie;

    @GetMapping("profile/{uid}")
    @ResponseBody
    public String getProfile(@PathVariable String uid) {
        String cookie = defaultCookie;
        String result = helper.requestBody(mihoyoApi.getProfile(MihoyoUtils.getServerNameFromUid(uid), uid, cookie), String.class);
        return result;
    }

    @GetMapping("daily-note/{uid}")
    @ResponseBody
    public String getDailyNote(@PathVariable String uid) {
        String cookie = defaultCookie;
        String result = helper.requestBody(mihoyoApi.getDailyNote(MihoyoUtils.getServerNameFromUid(uid), uid, cookie), String.class);
        return result;
    }
}
