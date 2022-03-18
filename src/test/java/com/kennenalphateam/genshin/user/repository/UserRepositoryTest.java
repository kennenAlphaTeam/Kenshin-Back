package com.kennenalphateam.genshin.user.repository;

import com.kennenalphateam.genshin.CustomDataJpaTest;
import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.util.OAuthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@CustomDataJpaTest
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void 유저를_생성하고_가져온다() {
        String oauthId = "oauth_id";
        OAuthType oAuthType = OAuthType.GOOGLE;
        User user = User.builder()
                .oauthId(oauthId)
                .oauthType(oAuthType)
                .build();

        User savedUser = userRepository.save(user);
        assertEquals(user.getOauthId(), savedUser.getOauthId());

        User findUser = userRepository.findByOauthTypeAndOauthId(oAuthType, oauthId).get();
        assertEquals(savedUser.getId(), findUser.getId());
        assertEquals(user.getOauthId(), findUser.getOauthId());
    }

    @Test
    void 잘못된_유저_insert시_Exception() {
        User user = User.builder()
                .oauthType(OAuthType.GOOGLE)
                .build();
        assertThrows(Exception.class, () -> {
            userRepository.save(user);
        });
    }

    @Test
    void 존재하지않는_유저_findById하면_실패한다() {
        assertTrue(userRepository.findById(1L).isEmpty());
        assertTrue(userRepository.findById(-1L).isEmpty());
        assertTrue(userRepository.findById(0L).isEmpty());
    }

    @Test
    void 존재하지않는_유저_findByOAuthTypeAndOauthId하면_실패한다() {
        assertTrue(userRepository.findByOauthTypeAndOauthId(OAuthType.GOOGLE, "oauth_id").isEmpty());
        assertTrue(userRepository.findByOauthTypeAndOauthId(null, "oauth_id").isEmpty());
        assertTrue(userRepository.findByOauthTypeAndOauthId(OAuthType.GOOGLE, null).isEmpty());
        assertTrue(userRepository.findByOauthTypeAndOauthId(null, null).isEmpty());
    }
}