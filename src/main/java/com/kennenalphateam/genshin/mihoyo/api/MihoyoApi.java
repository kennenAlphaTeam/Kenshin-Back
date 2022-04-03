package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.mihoyo.dto.CharacterRequest;
import com.kennenalphateam.genshin.mihoyo.dto.MihoyoGameCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "mihoyo", url = "https://bbs-api-os.mihoyo.com", configuration = MihoyoFeignConfiguration.class)
public interface MihoyoApi {
    @GetMapping("game_record/genshin/api/index")
    String getProfile(@RequestParam("server") String server, @RequestParam("role_id") String gensinUid);

    @GetMapping("game_record/genshin/api/dailyNote")
    String getDailyNote(@RequestParam("server") String server, @RequestParam("role_id") String gensinUid);

    @GetMapping("game_record/card/wapi/getGameRecordCard")
    MihoyoGameCardDto getGameRecordCard(@RequestParam("uid") String mihoyoId);

    @PostMapping("game_record/genshin/api/character")
    String getCharacterInfoList(@RequestBody CharacterRequest request);
}
