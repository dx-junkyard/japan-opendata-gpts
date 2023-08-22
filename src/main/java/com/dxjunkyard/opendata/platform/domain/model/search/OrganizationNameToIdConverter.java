package com.dxjunkyard.opendata.platform.domain.model.search;

import com.dxjunkyard.opendata.platform.domain.model.OrganizationId;
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
public class OrganizationNameToIdConverter {

    private final Map<String, OrganizationId> map;

    @NonNull
    public static OrganizationNameToIdConverter init() {
        return new OrganizationNameToIdConverter(new HashMap<>());
    }

    @NonNull
    public static OrganizationNameToIdConverter empty() {
        return init().freeze();
    }

    public void add(final String name, final String id) {
        try {
            map.put(name, OrganizationId.from(id));
        } catch (UnsupportedOperationException e) {
            log.error("組織の追加に失敗したので、何もせずに処理を継続します。", e);
        }
    }

    @Nullable
    public OrganizationId convert(final String name) {
        return map.get(name);
    }

    @NonNull
    public OrganizationNameToIdConverter freeze() {
        return new OrganizationNameToIdConverter(Collections.unmodifiableMap(map));
    }

    public boolean contains(final String name) {
        return map.containsKey(name);
    }

    @NonNull
    public Set<String> getOrganizationNameSet() {
        return map.keySet();
    }
}
