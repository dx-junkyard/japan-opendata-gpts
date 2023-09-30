package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

@RequiredArgsConstructor
public class AreaSearchCondition {

    @Nullable
    private final String area;

    @NonNull
    public static AreaSearchCondition of(final String area) {
        return new AreaSearchCondition(area);
    }

    @Nullable
    public String getRaw() {
        return area;
    }

    @NonNull
    public String getArea() {
        return Optional.ofNullable(area)
            .map(query -> String.format("\"%s\"", query))
            .orElse(StringUtils.EMPTY);
    }
}
