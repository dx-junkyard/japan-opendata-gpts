package com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.organization;

import com.dxjunkyard.opendata.platform.domain.model.OrganizationCount;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenDataOrganizationResponse(
    ResultResponse result
) {

    @NonNull
    public OrganizationNameToIdConverter convertOrganization() {
        final var organizationNameToIdConverter = OrganizationNameToIdConverter.init();

        getOrganizationCountSet().forEach(organizationCount -> {

            organizationNameToIdConverter.add(
                organizationCount.getId().getValue(),
                organizationCount.getId().getValue());
        });

        return organizationNameToIdConverter.freeze();
    }

    @NonNull
    public List<OrganizationCount> getOrganizationCountSet() {
        return Optional.ofNullable(result)
            .map(ResultResponse::facets)
            .map(FacetsResponse::facetFields)
            .map(FacetFieldsResponse::toOrganizationCountList)
            .map(organizationCountList -> organizationCountList.stream()
                .filter(OrganizationCount::isUpperLimit)
                .sorted(Comparator
                    .comparing(OrganizationCount::getCount, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()
                )
                .limit(75)
                .collect(Collectors.toList()))
            .orElse(List.of());
    }



    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResultResponse(
        FacetsResponse facets
    ) {
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FacetsResponse(
        FacetFieldsResponse facetFields
    ) {
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FacetFieldsResponse(
        List<Object> organization
    ) {

        @NonNull
        public List<OrganizationCount> toOrganizationCountList() {
            final List<OrganizationCount> organizationCountList = new ArrayList<>();
            for (int i = 0; i < CollectionUtils.size(organization); i += 2) {
                final Object firstValue = organization.get(i);
                final Object secondValue = organization.get(i + 1);

                organizationCountList.add(OrganizationCount
                    .of((String) firstValue, (Integer) secondValue));
            }

            return organizationCountList;
        }
    }
}
