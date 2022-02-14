package com.kennenalphateam.genshin.mihoyo.dto;

import lombok.Data;

@Data
public class GenshinIdCard {
    private String genshinUid;
    private String nickname;

    public GenshinIdCard(MihoyoGameCard mihoyoGameCard) {
        this.genshinUid = mihoyoGameCard.getGenshinId();
        this.nickname = mihoyoGameCard.getNickname();
    }
}
