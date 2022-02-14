package com.kennenalphateam.genshin.mihoyo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MihoyoGameCard {

    @JsonProperty("game_role_id")
    private String genshinId;

    @JsonProperty("region")
    private String region;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("game_id")
    private Integer gameId;
}
