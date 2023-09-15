package com.dxjunkyard.opendata.platform.config;

import com.dxjunkyard.opendata.platform.domain.model.OpenDataFormat;
import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

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
