package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.auth.SessionUser;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import com.kennenalphateam.genshin.user.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;

@Slf4j
@RequestMapping("mihoyo")
@RestController
@RequiredArgsConstructor
public class MihoyoController {
    private final MihoyoService mihoyoService;

    @GetMapping("profile/{uid}")
    @ResponseBody
    public String getProfile(@PathVariable String uid, @LoginUser SessionUser user) {
        return mihoyoService.getProfile(uid, user.getMihoyoCookie());
    }

    @GetMapping("daily-note/{uid}")
    @ResponseBody
    public String getDailyNote(@PathVariable String uid, @LoginUser SessionUser user) {
        return mihoyoService.getDailyNote(uid, user.getMihoyoCookie());
    }
}
