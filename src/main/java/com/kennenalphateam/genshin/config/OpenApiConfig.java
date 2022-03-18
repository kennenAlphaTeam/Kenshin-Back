package com.kennenalphateam.genshin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    @Value("${app.name:Kenshinalpha}")
    private String appName;
    @Value("${app.version:0.0.1}")
    private String appVersion;

    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "Jwt Auth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(appName));
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )

                )
                .externalDocs(
                        new ExternalDocumentation()
                                .url("/auth/oauth/login/google")
                                .description("Google Login")
                )
                .info(new Info().title(apiTitle).version(appVersion));
    }
}
