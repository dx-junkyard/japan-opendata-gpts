package com.dxjunkyard.opendata.platform.infrastructure.dto.request.opendata.search;

import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://docs.ckan.org/en/2.8/api/#ckan.logic.action.get.package_search">ckan.logic.action.get.package_search</a>
 */
@Builder
public record OpenDataSearchRequest(
    @NonNull Integer start,
    @NonNull Integer rows,
    @Nullable String fq,
    @Nullable String q
) implements Serializable {

    private static final Integer DEFAULT_ROWS = 10;

    @NonNull
    static public OpenDataSearchRequest from(final SearchCondition searchCondition) {

        final String q = searchCondition.getAllQuery();

        final String resFormat = searchCondition.getFormatSet().stream()
            .map(openDataFormat -> "res_format:" + openDataFormat.toString())
            .collect(Collectors.joining(" AND "));

        final String fq = Optional.of(resFormat).filter(StringUtils::isNotBlank)
            .map(value -> "(" + value + ")").orElse("");

        return OpenDataSearchRequest.builder()
            .q(Optional.of(q).filter(StringUtils::isNotBlank).orElse(null))
            .fq(Optional.of(fq).filter(StringUtils::isNotBlank).orElse(null))
            .start(getStart(searchCondition.getPage()))
            .rows(DEFAULT_ROWS)
            .build();
    }

    @NonNull
    private static Integer getStart(@NonNull final Integer page) {
        return (page - 1) * DEFAULT_ROWS;
    }
}
