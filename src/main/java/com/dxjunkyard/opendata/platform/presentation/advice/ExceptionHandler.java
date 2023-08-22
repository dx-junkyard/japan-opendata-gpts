package com.dxjunkyard.opendata.platform.presentation.advice;

import com.dxjunkyard.opendata.platform.presentation.dto.response.OpenDataSearcherErrorResponse;
import com.dxjunkyard.opendata.platform.presentation.dto.response.OpenDataSearcherErrorResponsePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * ExceptionHandler
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Mono<Void> handle(@NonNull final ServerWebExchange exchange, @NonNull final Throwable ex) {

        log.error(ex.getMessage(), ex);

        final ResponseEntity<OpenDataSearcherErrorResponse> errorResponse = createErrorResponse(ex);

        try {
            final DefaultDataBuffer db = new DefaultDataBufferFactory()
                .wrap(objectMapper.writeValueAsBytes(errorResponse.getBody()));

            exchange.getResponse().setStatusCode(errorResponse.getStatusCode());
            exchange.getResponse().getHeaders().addAll(errorResponse.getHeaders());

            return exchange.getResponse().writeWith(Mono.just(db));
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }

        return Mono.error(ex);
    }

    /**
     * エラーレスポンスを作成する.
     *
     * @param ex 例外
     * @return OpenDataSearcherErrorResponse
     */
    @NonNull
    private ResponseEntity<OpenDataSearcherErrorResponse> createErrorResponse(final Throwable ex) {
        if (ex instanceof MethodNotAllowedException) {
            return OpenDataSearcherErrorResponsePattern.METHOD_NOT_ALLOWED.createResponse();
        } else if (ex instanceof ResponseStatusException) {
            return OpenDataSearcherErrorResponsePattern.NOT_FOUND.createResponse();
        } else {
            return OpenDataSearcherErrorResponsePattern.INTERNAL_SERVER_ERROR.createResponse();
        }
    }
}
