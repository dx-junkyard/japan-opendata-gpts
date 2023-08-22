package com.dxjunkyard.opendata.platform.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * エラーレスポンスクラス.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record OpenDataSearcherErrorResponse(
    @Schema(description = "Http Status.")
    @NonNull Integer status,

    @Schema(description = "Title of error.")
    @NonNull String title
) implements Serializable {
}
