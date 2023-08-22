package com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.opendata.japan;

import com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.config.WebClientConfig;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("doc")
@Validated
@ConfigurationProperties(prefix = "repository.open-data.japan")
@RequiredArgsConstructor
public class JapanOpenDataRepositoryConfig {

    @NotNull
    private final String url;

    @NotNull
    @Getter
    private final String searchOpenDataPath;

    @Bean
    public WebClient japanOpenDataClient(final WebClient.Builder builder) {
        return builder.baseUrl(url + searchOpenDataPath)
            .exchangeStrategies(WebClientConfig.exchangeStrategies())
            .filter(WebClientConfig.logRequest())
            .filter(WebClientConfig.logResponse())
            .build();
    }
}
