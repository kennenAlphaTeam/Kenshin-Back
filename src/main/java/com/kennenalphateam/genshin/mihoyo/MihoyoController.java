package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.auth.SessionUser;
import com.kennenalphateam.genshin.user.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("mihoyo")
@RestController
@RequiredArgsConstructor
public class MihoyoController {
    private final MihoyoService mihoyoService;

    @GetMapping("profile/{genshinUid}")
    public String getProfile(@LoginUser SessionUser user, @PathVariable String genshinUid) {
        return mihoyoService.getProfile(genshinUid, user.getMihoyoCookie());
    }

    @GetMapping("me/profile")
    public String getMyProfile(@LoginUser SessionUser user) {
        return  mihoyoService.getProfile(user.getGenshinUid(), user.getMihoyoCookie());
    }

    @GetMapping("me/daily-note")
    public String getDailyNote(@LoginUser SessionUser user) {
        return mihoyoService.getDailyNote(user.getGenshinUid(), user.getMihoyoCookie());
    }

    @GetMapping("character")
    public String getCharacterInfoList(@LoginUser SessionUser user, @RequestParam(name = "characterIds") int[] characterIds) {
        return mihoyoService.getCharacterInfoList(user.getGenshinUid(), characterIds, user.getMihoyoCookie());
    }
}
