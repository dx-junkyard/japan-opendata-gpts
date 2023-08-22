package com.dxjunkyard.opendata.platform.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "ai-plugin")
@RequiredArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AiPluginConfig {

    @NotNull
    private final String schemaVersion;

    @NotNull
    private final String nameForHuman;

    @NotNull
    private final String nameForModel;

    @NotNull
    private final String descriptionForHuman;

    @NotNull
    private final String descriptionForModel;

    @NotNull
    private final AiPluginAuth auth;

    @NotNull
    private final AiPluginApi api;

    @NotNull
    private final String logoUrl;

    @NotNull
    private final String contactEmail;

    @NotNull
    private final String legalInfoUrl;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record AiPluginAuth(String type) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record AiPluginApi(String type, String url) {
    }
}
