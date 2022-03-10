package com.kennenalphateam.genshin.user.entity;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;
import com.kennenalphateam.genshin.mihoyo.MihoyoUtils;
import com.kennenalphateam.genshin.mihoyo.dto.GenshinIdCard;
import com.kennenalphateam.genshin.user.util.OAuthType;
import com.kennenalphateam.genshin.user.util.OAuthTypeConverter;
import com.kennenalphateam.genshin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "genshin", name = "`user`")
@ToString(exclude = {"mihoyoCookie"})
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_id", nullable = false)
    @Setter
    private String oauthId;

    @Column(name = "oauth_type", nullable = false)
    @Convert(converter = OAuthTypeConverter.class)
    @Setter
    private OAuthType oauthType;

    @Column(name = "mihoyo_cookie")
    @Setter
    private String mihoyoCookie;

    @Column(name = "mihoyo_id")
    @Setter
    private String mihoyoId;

    @Column(name = "genshin_uid")
    @Setter
    private String genshinUid;

    @Column(name = "nickname")
    @Setter
    private String nickname;

    @Builder
    public User(Long id, String oauthId, OAuthType oauthType, String mihoyoCookie, String mihoyoId, String genshinUid, String nickname) {
        this.id = id;
        this.oauthId = oauthId;
        this.oauthType = oauthType;
        this.mihoyoCookie = mihoyoCookie;
        this.mihoyoId = mihoyoId;
        this.genshinUid = genshinUid;
        this.nickname = nickname;
    }

    public void updateCookie(String mihoyoCookie) {
        this.mihoyoCookie = mihoyoCookie;
        this.mihoyoId = MihoyoUtils.getMihoyoUidFromCookie(mihoyoCookie);
    }

    public void updateGenshinId(GenshinIdCard genshinIdCard) {
        this.genshinUid = genshinIdCard.getGenshinUid();
        this.nickname = genshinIdCard.getNickname();
    }
}
