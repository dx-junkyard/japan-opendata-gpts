package com.dxjunkyard.opendata.platform.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrganizationId {

    @NonNull
    private final String value;

    @NonNull
    public static OrganizationId from(final String value) {

        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("value is blank." + value);
        }

        return new OrganizationId(value);
    }

    @Override
    @NonNull
    public String toString() {
        return value;
    }
}
