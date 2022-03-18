package com.kennenalphateam.genshin.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class AuthUtils {
    private static final String DEFAULT_AUTHORITY = "ROLE_USER";

    public static Collection<? extends GrantedAuthority> getDefaultAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(DEFAULT_AUTHORITY));
    }
}
