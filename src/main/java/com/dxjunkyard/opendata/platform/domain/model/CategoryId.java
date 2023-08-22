package com.dxjunkyard.opendata.platform.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategoryId {

    @NonNull
    private final String value;

    @NonNull
    public static CategoryId from(final String value) {

        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("value is blank." + value);
        }

        return new CategoryId(value);
    }

    @Override
    @NonNull
    public String toString() {
        return value;
    }
}
