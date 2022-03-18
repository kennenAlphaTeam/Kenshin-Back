package com.kennenalphateam.genshin.mihoyo.dto;

import com.kennenalphateam.genshin.auth.jwt.JwtUser;
import lombok.Data;

@Data
public class GenshinIdCard {
    private String genshinUid;
    private String nickname;

    public GenshinIdCard(MihoyoGameCard mihoyoGameCard) {
        this.genshinUid = mihoyoGameCard.getGenshinId();
        this.nickname = mihoyoGameCard.getNickname();
    }

    public GenshinIdCard(JwtUser user) {
        this.genshinUid = user.getGenshinUid();
        this.nickname = user.getNickname();
    }
}
