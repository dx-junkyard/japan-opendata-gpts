package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import com.dxjunkyard.opendata.platform.domain.model.CategoryId;
import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategorySearchCondition {

    @NonNull
    private final CategoryNameToIdConverter categoryNameToIdConverter;

    @NonNull
    private final Set<String> categoryNameSet;

    public static CategorySearchCondition of(final CategoryNameToIdConverter categoryNameToIdConverter,
                                             final Set<String> categoryNameSet) {
        return new CategorySearchCondition(categoryNameToIdConverter, categoryNameSet);
    }

    private Map<String, CategoryId> filter() {
        return categoryNameSet.stream()
            .filter(categoryNameToIdConverter::contains)
            .collect(Collectors.toUnmodifiableMap(
                key -> key,
                categoryNameToIdConverter::convert));
    }


    @Nullable
    public String getCategory() {

        final var map = filter();

        if (map.isEmpty()) {
            return null;
        }

        return String.join(StringUtils.SPACE, map.keySet());
    }

    @NonNull
    public Set<CategoryId> getCategoryIdSet() {
        return Set.copyOf(filter().values());
    }

    @NonNull
    public Set<String> getCategoryQuery() {
        return categoryNameSet.stream()
            .filter(categoryName -> !categoryNameToIdConverter.contains(categoryName))
            .collect(Collectors.toUnmodifiableSet());
    }
}
