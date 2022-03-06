package com.kennenalphateam.genshin.mihoyo.dto;

import com.kennenalphateam.genshin.auth.SessionUser;
import lombok.Data;

@Data
public class GenshinIdCard {
    private String genshinUid;
    private String nickname;

    public GenshinIdCard(MihoyoGameCard mihoyoGameCard) {
        this.genshinUid = mihoyoGameCard.getGenshinId();
        this.nickname = mihoyoGameCard.getNickname();
    }

    public GenshinIdCard(SessionUser sessionUser) {
        this.genshinUid = sessionUser.getGenshinUid();
        this.nickname = sessionUser.getNickname();
    }
}
