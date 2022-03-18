package com.kennenalphateam.genshin.auth.jwt;

import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.util.OAuthType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    private MockedStatic<Clock> clockMock;

    private final String JWT_SECRET = "THIS_IS_JWT_SERVICE_TESTS_JWT_SECRET!!!";
    private final long JWT_VALIDITY_IN_SECONDS = 3600;
    private final User TEST_USER = new User(1L, "oauth_id", OAuthType.GOOGLE, "cookie", "mihoyoid", "genshinId", "nickname");
    private final JwtService jwtService = new JwtService(JWT_SECRET, JWT_VALIDITY_IN_SECONDS);

    private void mockInstant(long aspect) {
        if (clockMock != null)
            clockMock.close();
        Clock spyClock = spy(Clock.class);
        clockMock = mockStatic(Clock.class);
        clockMock.when(Clock::systemUTC).thenReturn(spyClock);
        when(spyClock.instant()).thenReturn(Instant.ofEpochMilli(aspect));
    }

    @Test
    void 현재_시각에서_VALIDITY_IN_SECONDS_만큼_더한다() {
        String nowTimeStamp = "2022-02-22T00:00:00Z";
        mockInstant(Instant.parse(nowTimeStamp).toEpochMilli());

        Instant exp = Instant.now().plusSeconds(JWT_VALIDITY_IN_SECONDS);

        assertEquals(exp.toEpochMilli(), jwtService.generateExp().getTime());
        clockMock.close();
    }

    @Test
    void 정상적으로_토큰을_생성하고_읽는다() {
        JwtUser jwtUser = new JwtUser(TEST_USER);

        String token = jwtService.generateToken(jwtUser);
        assertNotNull(token);
        JwtUser result = jwtService.validateTokenAndGetUser(token);
        assertEquals(jwtUser.getUserId(), result.getUserId());
    }

    @Test
    void 만료된_토큰은_Exception_발생한다() {
        mockInstant(Instant.now().minusSeconds(JWT_VALIDITY_IN_SECONDS).toEpochMilli());

        JwtUser jwtUser = new JwtUser(TEST_USER);

        String token = jwtService.generateToken(jwtUser);

        assertThrows(Exception.class, () -> {
            jwtService.validateTokenAndGetUser(token);
        });
        clockMock.close();
    }
}