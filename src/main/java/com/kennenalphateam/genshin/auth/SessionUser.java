package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {

    private Long userId;
    private String mihoyoCookie;
    private String genshinUid;

    public SessionUser(User user) {
        this.mihoyoCookie = user.getMihoyoCookie();
        this.userId = user.getId();
        this.genshinUid = user.getGenshinUid();
    }
}
