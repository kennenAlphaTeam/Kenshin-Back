package com.kennenalphateam.genshin.auth.jwt;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;
import com.kennenalphateam.genshin.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Schema(hidden = true)
public class JwtUser implements Serializable {

    private Long userId;
    private String genshinUid;
    private String nickname;

    public JwtUser(User user) {
        this.userId = user.getId();
        this.genshinUid = user.getGenshinUid();
        this.nickname = user.getNickname();
    }

    public void checkRegisteredGenshinUser() throws ErrorException {
        if (genshinUid == null || genshinUid.isEmpty())
            throw new ErrorException(ErrorCode.UNREGISTERED_GENSHIN_USER);
    }
}
