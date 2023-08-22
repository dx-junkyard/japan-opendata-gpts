package com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import reactor.core.publisher.Mono;

@Slf4j
public class WebClientConfig {
    private static final int MAX_IN_MEMORY_SIZE = 10 * 1024 * 1024;

    public static ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return next.exchange(clientRequest);
        };
    }

    public static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    public static ExchangeStrategies exchangeStrategies() {
        return ExchangeStrategies
            .builder()
            .codecs(codecs -> codecs
                .defaultCodecs()
                .maxInMemorySize(MAX_IN_MEMORY_SIZE))
            .build();
    }
}
