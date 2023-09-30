package com.dxjunkyard.opendata.platform.presentation.dto.response;

import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public record SearchConditionResponse(
    Integer page,
    String area,
    String keyword,
    String format
) implements Serializable {

    @NonNull
    public static SearchConditionResponse empty() {
        return new SearchConditionResponse(1, null, null,  null);
    }

    @NonNull
    public static SearchConditionResponse from(final SearchCondition searchCondition) {
        return new SearchConditionResponse(
            searchCondition.getPage(),
            searchCondition.getRawArea(),
            searchCondition.getRawKeyword(),
            searchCondition.getFormat()
        );
    }
}
