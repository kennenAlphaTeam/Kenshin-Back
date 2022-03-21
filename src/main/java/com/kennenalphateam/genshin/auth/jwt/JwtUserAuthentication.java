package com.kennenalphateam.genshin.auth.jwt;

import com.kennenalphateam.genshin.auth.AuthUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class JwtUserAuthentication extends UsernamePasswordAuthenticationToken {
    public JwtUserAuthentication(JwtUser user) {
        super(user, null, AuthUtils.getDefaultAuthorities());
    }
}
