package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import com.dxjunkyard.opendata.platform.domain.model.OrganizationId;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrganizationSearchCondition {

    @NonNull
    private final OrganizationNameToIdConverter organizationNameToIdConverter;

    @NonNull
    private final Set<String> organizationNameSet;

    public static OrganizationSearchCondition of(final OrganizationNameToIdConverter organizationNameToIdConverter,
                                                 final Set<String> organizationNameSet) {
        return new OrganizationSearchCondition(organizationNameToIdConverter, organizationNameSet);
    }

    private Map<String, OrganizationId> filter() {
        return organizationNameSet.stream()
            .filter(organizationNameToIdConverter::contains)
            .collect(Collectors.toUnmodifiableMap(
                key -> key,
                organizationNameToIdConverter::convert));
    }


    @Nullable
    public String getOrganization() {

        final var map = filter();

        if (map.isEmpty()) {
            return null;
        }

        return String.join(StringUtils.SPACE, map.keySet());
    }

    @NonNull
    public Set<OrganizationId> getOrganizationIdSet() {
        return Set.copyOf(filter().values());
    }

    @NonNull
    public Set<String> getOrganizationQuery() {
        return organizationNameSet.stream()
            .filter(organizationName -> !organizationNameToIdConverter.contains(organizationName))
            .collect(Collectors.toUnmodifiableSet());
    }
}
