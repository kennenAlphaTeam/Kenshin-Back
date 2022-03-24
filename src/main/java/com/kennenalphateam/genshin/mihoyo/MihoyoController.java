package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.user.LoginUser;
import com.kennenalphateam.genshin.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "mihoyo", produces = "application/json")
@RestController
@RequiredArgsConstructor
public class MihoyoController {
    private final MihoyoService mihoyoService;

    @GetMapping("profile/{genshinUid}")
    public String getProfile(@LoginUser User user, @PathVariable String genshinUid) {
        return mihoyoService.getProfile(genshinUid, user.getMihoyoCookie());
    }

    @GetMapping("me/profile")
    public String getMyProfile(@LoginUser User user) {
        return  mihoyoService.getProfile(user.getGenshinUid(), user.getMihoyoCookie());
    }

    @GetMapping("me/daily-note")
    public String getDailyNote(@LoginUser User user) {
        return mihoyoService.getDailyNote(user.getGenshinUid(), user.getMihoyoCookie());
    }

    @GetMapping("character")
    public String getCharacterInfoList(@LoginUser User user) {
        return mihoyoService.getCharacterInfoList(user.getGenshinUid(), user.getMihoyoCookie());
    }
}
