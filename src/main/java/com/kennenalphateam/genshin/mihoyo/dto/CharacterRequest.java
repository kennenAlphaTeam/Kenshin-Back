package com.kennenalphateam.genshin.mihoyo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class CharacterRequest {
    @JsonProperty("character_ids")
    int[] characterIds;
    @JsonProperty("role_id")
    String genshinUid;
    @JsonProperty("server")
    String server;

    @Builder
    public CharacterRequest(int[] characterIds, String genshinUid, String server) {
        this.characterIds = characterIds;
        this.genshinUid = genshinUid;
        this.server = server;
    }
}
