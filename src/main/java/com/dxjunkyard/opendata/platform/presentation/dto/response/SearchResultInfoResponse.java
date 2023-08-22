package com.dxjunkyard.opendata.platform.presentation.dto.response;

import java.io.Serializable;

public record SearchResultInfoResponse(
    Integer totalOfHits
) implements Serializable {
    public static SearchResultInfoResponse empty() {
        return new SearchResultInfoResponse(0);
    }
}
