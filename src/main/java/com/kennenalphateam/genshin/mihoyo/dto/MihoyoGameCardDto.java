package com.kennenalphateam.genshin.mihoyo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MihoyoGameCardDto {
    @JsonProperty("list")
    MihoyoGameCard[] gameCards;
}
