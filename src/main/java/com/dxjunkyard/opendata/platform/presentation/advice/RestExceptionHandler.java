package com.dxjunkyard.opendata.platform.presentation.advice;

import com.dxjunkyard.opendata.platform.presentation.controller.AiPluginController;
import com.dxjunkyard.opendata.platform.presentation.controller.OpenDataSearcherController;
import com.dxjunkyard.opendata.platform.presentation.dto.response.OpenDataSearcherErrorResponse;
import com.dxjunkyard.opendata.platform.presentation.dto.response.OpenDataSearcherErrorResponsePattern;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

/**
 * RestExceptionHandler
 */
@RequiredArgsConstructor
@RestControllerAdvice(basePackageClasses = {OpenDataSearcherController.class, AiPluginController.class})
@Slf4j
public class RestExceptionHandler {

    /**
     * ValidationExceptionの例外ハンドリング.
     *
     * @param e                 ValidationException
     * @return Mono<OpenDataSearcherErrorResponse>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ResponseEntity<OpenDataSearcherErrorResponse>> handleBadRequestException(final ValidationException e) {
        log.error(e.getMessage(), e);
        return Mono.just(OpenDataSearcherErrorResponsePattern.BAD_REQUEST.createResponse());
    }

    /**
     * TypeMismatchExceptionの例外ハンドリング.
     *
     * @param e                 TypeMismatchException
     * @return Mono<OpenDataSearcherErrorResponse>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ResponseEntity<OpenDataSearcherErrorResponse>> handleBadRequestException(final TypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Mono.just(OpenDataSearcherErrorResponsePattern.BAD_REQUEST.createResponse());
    }

    /**
     * WebExchangeBindExceptionの例外ハンドリング.
     *
     * @param e                 TypeMismatchException
     * @return Mono<OpenDataSearcherErrorResponse>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ResponseEntity<OpenDataSearcherErrorResponse>> handleBadRequestException(final WebExchangeBindException e) {
        log.error(e.getMessage(), e);
        return Mono.just(OpenDataSearcherErrorResponsePattern.BAD_REQUEST.createResponse());
    }

    /**
     * その他の例外ハンドリング.
     *
     * @param e                 例外
     * @return Mono<OpenDataSearcherErrorResponse>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Mono<ResponseEntity<OpenDataSearcherErrorResponse>> handleBadRequestException(final Throwable e) {
        log.error(e.getMessage(), e);
        return Mono.just(OpenDataSearcherErrorResponsePattern.INTERNAL_SERVER_ERROR.createResponse());
    }
}
