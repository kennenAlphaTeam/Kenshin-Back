package com.kennenalphateam.genshin.auth;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Builder
    public UserAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseEntity<ErrorResponse> responseEntity = ErrorResponse.toResponseEntity(ErrorCode.USER_UNAUTHORIZED_ERROR);
        response.setStatus(responseEntity.getStatusCodeValue());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseEntity.getBody()));
        response.flushBuffer();
    }
}
