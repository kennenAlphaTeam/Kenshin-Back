package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import com.kennenalphateam.genshin.user.util.OAuthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomOAuth2UserServiceTest {

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 새로운_유저가_로그인하면_데이터를_저장한다() {
        final String oauthId = "test_oauth_id";
        final OAuthType oauthType = OAuthType.GOOGLE;

        assertTrue(userRepository.findByOauthTypeAndOauthId(oauthType, oauthId).isEmpty());

        User user = customOAuth2UserService.saveOrLoadUser(oauthType.name(), oauthId);
        assertEquals(oauthId, user.getOauthId());
        assertEquals(oauthType, user.getOauthType());
        assertEquals(user, userRepository.findByOauthTypeAndOauthId(oauthType, oauthId).get());
    }

    @Test
    public void 유저가_여러번_로그인하면_데이터를_불러온다() {
        final String oauthId = "test_oauth_id";
        final String registrationId = OAuthType.GOOGLE.name();

        customOAuth2UserService.saveOrLoadUser(registrationId, oauthId);
        assertTrue(userRepository.findByOauthTypeAndOauthId(OAuthType.GOOGLE, oauthId).isPresent());
        User user = customOAuth2UserService.saveOrLoadUser(registrationId, oauthId);
        assertEquals(userRepository.count(), 1);
    }
}
