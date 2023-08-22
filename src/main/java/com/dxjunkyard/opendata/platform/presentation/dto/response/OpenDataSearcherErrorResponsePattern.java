package com.dxjunkyard.opendata.platform.presentation.dto.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

@RequiredArgsConstructor
public enum OpenDataSearcherErrorResponsePattern {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found"),
    ;

    @NonNull
    private final HttpStatus status;

    @NonNull
    private final String title;

    /**
     * HttpStatusからAbJudgeErrorResponseを取得する.
     *
     * @return AbJudgeErrorResponse
     */
    @NonNull
    public ResponseEntity<OpenDataSearcherErrorResponse> createResponse() {
        return ResponseEntity
            .status(status)
            .contentType(APPLICATION_PROBLEM_JSON)
            .body(OpenDataSearcherErrorResponse.builder()
                .status(status.value())
                .title(title)
                .build());
    }
}
