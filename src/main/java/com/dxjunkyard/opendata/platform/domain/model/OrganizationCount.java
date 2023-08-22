package com.dxjunkyard.opendata.platform.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@Getter
public class OrganizationCount {

    @NonNull
    private final OrganizationId id;

    @NonNull
    private final Integer count;

    @NonNull
    public static OrganizationCount of(final String idStr, final Integer count) {
        return new OrganizationCount(OrganizationId.from(idStr), count);
    }

    public boolean isUpperLimit() {
        return count >= 10;
    }
}
