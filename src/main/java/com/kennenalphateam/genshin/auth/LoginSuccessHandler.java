package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.auth.jwt.JwtService;
import com.kennenalphateam.genshin.auth.jwt.JwtUser;
import com.kennenalphateam.genshin.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User authUser = (OAuth2User) authentication.getPrincipal();
        User user = (User) authUser.getAttributes().get("user");

        log.info("OAuth user login success user={}", user);
        response.addCookie(jwtService.generateTokenCookie(new JwtUser(user)));
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
