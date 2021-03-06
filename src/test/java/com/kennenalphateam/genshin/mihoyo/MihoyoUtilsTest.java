package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.exception.InvalidCookieException;
import com.kennenalphateam.genshin.mihoyo.exception.InvalidUidException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MihoyoUtilsTest {

    @Test
    void DS_토큰을_생성한다() {
        String ds1 = MihoyoUtils.generateDSToken();
        String ds2 = MihoyoUtils.generateDSToken();

        assertNotNull(ds1);
        assertNotEquals(ds1.length(), 0);
        assertNotEquals(ds1, ds2);
    }

    @Test
    void 아시아_UID에서_서버_이름을_가져온다() {
        String uid = "819688319";
        String serverName = MihoyoUtils.getServerNameFromUid(uid);

        assertEquals(serverName, MihoyoUtils.MihoyoServer.ASIA.serverName);
    }

    @Test
    void 잘못된_UID에서_서버_이름을_가져오면_예외를_던진다() {
        String uid = "319688319";

        assertThrows(InvalidUidException.class, () -> {
            MihoyoUtils.getServerNameFromUid(uid);
        });
    }

    @Test
    void 정상적인_Cookie_형식에서_UID를_불러온다() {
        String cookie = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltuid=135492127";
        String cookie2 = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltuid=135492127;;";
        String cookie3 = "account_id=135492127; ltuid=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA;";

        String uid = MihoyoUtils.getMihoyoUidFromCookie(cookie);
        assertEquals("135492127", uid);

        uid = MihoyoUtils.getMihoyoUidFromCookie(cookie2);
        assertEquals("135492127", uid);

        uid = MihoyoUtils.getMihoyoUidFromCookie(cookie3);
        assertEquals("135492127", uid);
    }

    @Test
    void 비정상적인_Cookie_형식에서_UID를_불러온다() {
        String cookie = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltuid;=135492127";
        String cookie2 = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; tuid=135492127";
        String cookie3 = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltui=135492127;";
        String cookie4 = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltuid=;135492127;;";


        assertThrows(InvalidCookieException.class, () -> {
            MihoyoUtils.getMihoyoUidFromCookie(cookie);
        });

        assertThrows(InvalidCookieException.class, () -> {
            MihoyoUtils.getMihoyoUidFromCookie(cookie2);
        });

        assertThrows(InvalidCookieException.class, () -> {
            MihoyoUtils.getMihoyoUidFromCookie(cookie3);
        });

        assertThrows(InvalidCookieException.class, () -> {
            MihoyoUtils.getMihoyoUidFromCookie(cookie4);
        });
    }

    @Test
    void minifyCookieTest() {
        String cookie = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltuid=135492127";
        String anyString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry";
        String cookie2 = "account_id=135492127; ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA; ltui=135492127;";

        assertEquals("ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA;ltuid=135492127", MihoyoUtils.minifyMihoyoCookie(cookie));
        assertEquals("", MihoyoUtils.minifyMihoyoCookie(anyString));
        assertEquals("ltoken=ct40Q2reTSLDIpW6i193h7nuru54LAPqSPVYjenA", MihoyoUtils.minifyMihoyoCookie(cookie2));
    }
}