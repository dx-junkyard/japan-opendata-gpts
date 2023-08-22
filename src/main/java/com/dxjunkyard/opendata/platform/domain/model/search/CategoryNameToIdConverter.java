package com.dxjunkyard.opendata.platform.domain.model.search;

import com.dxjunkyard.opendata.platform.domain.model.CategoryId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CategoryNameToIdConverter {

    @NonNull
    private final Map<String, CategoryId> map;

    @NonNull
    public static CategoryNameToIdConverter init() {
        return new CategoryNameToIdConverter(new HashMap<>());
    }

    @NonNull
    public static CategoryNameToIdConverter empty() {
        return init().freeze();
    }

    public void add(final String name, final String id) {
        try {
            map.put(name, CategoryId.from(id));
        } catch (UnsupportedOperationException e) {
            log.error("カテゴリの追加に失敗したので、何もせずに処理を継続します。", e);
        }
    }

    @Nullable
    public CategoryId convert(final String name) {
        return map.get(name);
    }

    @NonNull
    public CategoryNameToIdConverter freeze() {
        return new CategoryNameToIdConverter(Collections.unmodifiableMap(map));
    }

    public boolean contains(final String name) {
        return map.containsKey(name);
    }

    @NonNull
    public Set<String> getCategoryNameSet() {
        return map.keySet();
    }
}
