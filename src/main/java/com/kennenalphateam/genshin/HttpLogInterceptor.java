package com.kennenalphateam.genshin;

import com.kennenalphateam.genshin.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Request from {} - {} {}", IpUtils.getIpFromRequest(request), request.getMethod(), request.getRequestURI());
        return true;
    }
}