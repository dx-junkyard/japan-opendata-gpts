package com.dxjunkyard.opendata.platform.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@Getter
public class CategoryCount {

    @NonNull
    private final CategoryId id;

    @NonNull
    private final Integer count;

    @NonNull
    public static CategoryCount of(final String idStr, final Integer count) {
        return new CategoryCount(CategoryId.from(idStr), count);
    }

    public boolean isUpperLimit() {
        return count >= 10;
    }
}
