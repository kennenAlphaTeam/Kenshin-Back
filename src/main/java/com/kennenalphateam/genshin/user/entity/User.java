package com.kennenalphateam.genshin.user.entity;

import com.kennenalphateam.genshin.user.util.OAuthType;
import com.kennenalphateam.genshin.user.util.OAuthTypeConverter;
import com.kennenalphateam.genshin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "genshin", name = "user")
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

    @Builder
    public User(String oauthId, OAuthType oauthType, String mihoyoCookie, String mihoyoId) {
        this.oauthId = oauthId;
        this.oauthType = oauthType;
        this.mihoyoCookie = mihoyoCookie;
        this.mihoyoId = mihoyoId;
    }

    public void updateCookie(String mihoyoCookie) {
        this.mihoyoCookie = mihoyoCookie;
    }
}
