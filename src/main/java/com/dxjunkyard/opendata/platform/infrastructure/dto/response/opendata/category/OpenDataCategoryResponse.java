package com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.category;

import com.dxjunkyard.opendata.platform.domain.model.CategoryCount;
import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
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
public record OpenDataCategoryResponse(
    ResultResponse result
) {

    @NonNull
    public CategoryNameToIdConverter convertCategory() {
        final var categoryNameToIdConverter = CategoryNameToIdConverter.init();

        getCategoryCountSet().forEach(organizationCount -> {

            categoryNameToIdConverter.add(
                organizationCount.getId().getValue(),
                organizationCount.getId().getValue());
        });

        return categoryNameToIdConverter.freeze();
    }

    public List<CategoryCount> getCategoryCountSet() {
        return Optional.ofNullable(result)
            .map(ResultResponse::facets)
            .map(FacetsResponse::facetFields)
            .map(FacetFieldsResponse::toCategoryCountList)
            .map(categoryCountList -> categoryCountList.stream()
                .filter(CategoryCount::isUpperLimit)
                .sorted(Comparator
                    .comparing(CategoryCount::getCount, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()
                )
                .limit(50)
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
        List<Object> tags
    ) {

        @NonNull
        public List<CategoryCount> toCategoryCountList() {
            final List<CategoryCount> organizationCountList = new ArrayList<>();
            for (int i = 0; i < CollectionUtils.size(tags); i += 2) {
                final Object firstValue = tags.get(i);
                final Object secondValue = tags.get(i + 1);

                organizationCountList.add(CategoryCount
                    .of((String) firstValue, (Integer) secondValue));
            }

            return organizationCountList;
        }
    }
}
