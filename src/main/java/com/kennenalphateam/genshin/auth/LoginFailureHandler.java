package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.util.IpUtils;
import com.kennenalphateam.genshin.util.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("OAuth Login failure ip = {}", IpUtils.getIpFromRequest(request));
        URL url = new URL(request.getRequestURL().toString());
        response.sendRedirect(UrlUtils.createRedirectUrl(url, "/login?error=1"));
    }
}
