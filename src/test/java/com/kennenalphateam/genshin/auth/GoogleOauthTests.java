package com.kennenalphateam.genshin.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GoogleOauthTests {

    @Test
    public void google로그인_시도하면_Oauth인증창_반환한다() {
        given()
                .when()
                    .redirects().follow(false)
                    .get("auth/oauth/login/google")
                .then()
                    .statusCode(302)
                    .header("Location", containsString("https://accounts.google.com/o/oauth2/v2/auth"));
    }
}
