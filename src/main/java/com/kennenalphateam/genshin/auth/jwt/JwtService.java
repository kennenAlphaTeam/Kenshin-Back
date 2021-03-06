package com.kennenalphateam.genshin.auth.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class JwtService {

    private static final String JWT_COOKIE_NAME = "uinfo";
    private static final String USER_CLAIM_NAME = "user";
    private final Key signKey;
    private final long validityInSeconds;

    @Autowired
    JwtService(@Value("${jwt.access.secret}") String secret,
               @Value("${jwt.access.validity-in-seconds}") long validityInSeconds) {
        this.signKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInSeconds = validityInSeconds;
    }

    public Date generateExp() {
        return Date.from(Instant.now().plusSeconds(validityInSeconds));
    }

    public String generateToken(JwtUser user) {
        return Jwts.builder()
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(generateExp())
                .claim(USER_CLAIM_NAME, user)
                .signWith(signKey)
                .compact();
    }

    public JwtUser validateTokenAndGetUser(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .deserializeJsonWith(new JacksonDeserializer(Maps.of(USER_CLAIM_NAME, JwtUser.class).build()))
                .setSigningKey(signKey)
                .build();
        parser.isSigned(token);
        return parser.parseClaimsJws(token).getBody().get(USER_CLAIM_NAME, JwtUser.class);
    }

    public Cookie generateTokenCookie(JwtUser user) {
        Cookie cookie = new Cookie(JWT_COOKIE_NAME, generateToken(user));
        cookie.setMaxAge((int) validityInSeconds);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        return cookie;
    }

    public String getJwtFromCookies(Cookie[] cookies) {
        if (cookies == null)
            return null;
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(JWT_COOKIE_NAME))
                .map(Cookie::getValue).findFirst()
                .orElse(null);
    }
}
