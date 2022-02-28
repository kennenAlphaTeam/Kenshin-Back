package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.api.MihoyoApi;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoApiException;
import com.kennenalphateam.genshin.mihoyo.api.MihoyoRetrofitHelper;
import com.kennenalphateam.genshin.mihoyo.dto.GenshinIdCard;
import com.kennenalphateam.genshin.mihoyo.dto.MihoyoGameCard;
import com.kennenalphateam.genshin.mihoyo.dto.MihoyoGameCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GenshinInfoService {
    private final MihoyoApi mihoyoApi;
    private final MihoyoRetrofitHelper helper;

    public GenshinIdCard getGenshinUidFromCookie(String cookie) {
        return new GenshinIdCard(getValidMihoyoGameCard(cookie));
    }

    public MihoyoGameCard getValidMihoyoGameCard(String cookie) {
        String mihoyoUid = MihoyoUtils.getMihoyoUidFromCookie(cookie);

        MihoyoGameCardDto body = helper.requestBody(mihoyoApi.getGameRecordCard(cookie, mihoyoUid), MihoyoGameCardDto.class);
        MihoyoGameCard[] cards = body.getGameCards();

        if (cards.length == 0)
            throw new MihoyoApiException();
        return Arrays.stream(cards).filter(this::isValidMihoyoGameCard).findFirst().orElseThrow(MihoyoApiException::new);
    }

    public boolean isValidMihoyoGameCard(MihoyoGameCard card) {
        if (card == null)
            return false;

        // asia 서버만 지원합니다.
        return card.getRegion().equals("os_asia") && card.getGameId() == 2;
    }
}
