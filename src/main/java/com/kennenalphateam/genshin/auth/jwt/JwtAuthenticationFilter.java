package com.kennenalphateam.genshin.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return isMatchesPath(request.getServletPath(), "/swagger*", "/*.js", "/*.ico", "/api-docs", "/actuator/*");
    }

    private boolean isMatchesPath(String path, String... patterns) {
        return Arrays.stream(patterns).anyMatch((p) -> pathMatcher.match(p, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.isNotEmpty(jwt)) {
                JwtUserAuthentication authentication = getJwtAuthentication(jwt);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("JwtAuthentication 저장에 실패했습니다.", ex);
        }

        filterChain.doFilter(request, response);
    }

    private JwtUserAuthentication getJwtAuthentication(String jwt) {
        JwtUser user = jwtService.validateTokenAndGetUser(jwt);

        return new JwtUserAuthentication(user);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        return jwtService.getJwtFromCookies(request.getCookies());
    }
}