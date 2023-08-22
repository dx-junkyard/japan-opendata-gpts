package com.dxjunkyard.opendata.platform.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Request Logging
 */
@Component
@Slf4j
public class RequestLoggingFilter implements WebFilter {

    /**
     * リクエストをログ出力する.
     *
     * @param exchange exchange
     * @param chain    chain
     * @return Mono
     */
    @NonNull
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, @NonNull final WebFilterChain chain) {
        final var request = exchange.getRequest();

        final String headers = request.getHeaders().entrySet().stream()
            .filter(entry -> !"Cookie".equals(entry.getKey()))
            .map(entry -> entry.getKey() + ": " + String.join(StringUtils.SPACE, entry.getValue()))
            .reduce((a, b) -> a + ", " + b)
            .orElse(StringUtils.EMPTY);

        if (!exchange.getRequest().getURI().getPath().startsWith("/actuator")) {
            log.info("Request: {} {} {}", request.getMethod(), request.getURI(), headers);
        }

        return chain.filter(exchange);
    }
}
