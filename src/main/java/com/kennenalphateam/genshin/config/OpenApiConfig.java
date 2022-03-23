package com.kennenalphateam.genshin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    @Value("${app.name:Kenshinalpha}")
    private String appName;
    @Value("${app.version:0.0.1}")
    private String appVersion;
    @Value("${app.forwarded-prefix:/}")
    private String serverPrefix;

    @Bean
    public OpenAPI openAPI() {
        final String apiTitle = String.format("%s API", StringUtils.capitalize(appName));
        if (!serverPrefix.endsWith("/"))
            serverPrefix = serverPrefix + "/";
        final String googleLoginUrl = serverPrefix + "auth/oauth/login/google";

        return new OpenAPI()
                .externalDocs(
                        new ExternalDocumentation()
                                .url(googleLoginUrl)
                                .description("Google Login")
                )
                .info(new Info().title(apiTitle).version(appVersion));
    }
}
