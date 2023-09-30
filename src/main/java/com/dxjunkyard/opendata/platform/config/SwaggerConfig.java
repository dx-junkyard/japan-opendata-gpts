package com.dxjunkyard.opendata.platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Profile("doc")
@Validated
@ConfigurationProperties(prefix = "springdoc.extension")
@RequiredArgsConstructor
public class SwaggerConfig {

    @NotNull
    private final String title;

    @NotNull
    private final String version;

    @NotNull
    private final String description;

    @NotNull
    private final String host;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .servers(List.of(
                new Server()
                    .url(host)))
            .info(new Info()
                .title(title)
                .version(version)
                .description(description));
    }
}
