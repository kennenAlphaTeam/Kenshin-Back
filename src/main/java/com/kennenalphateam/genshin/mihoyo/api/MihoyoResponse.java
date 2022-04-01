package com.kennenalphateam.genshin.mihoyo.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Slf4j
public class MihoyoResponse implements Serializable {
    private Integer retcode;
    private String message;
    private ObjectNode data;

    public boolean isSuccess() {
        return !(retcode == null || retcode != 0 || data == null);
    }
}
