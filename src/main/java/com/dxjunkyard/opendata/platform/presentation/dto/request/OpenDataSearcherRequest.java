package com.dxjunkyard.opendata.platform.presentation.dto.request;

import com.dxjunkyard.opendata.platform.domain.model.OpenDataFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenDataSearcherRequest(

    @Parameter(description = "Page number. Default is 1. Minimum is 1.")
    @NonNull
    @Min(1)
    Integer page,

    @Parameter(description = "Search keyword. If you want to search for multiple keywords, separate them with spaces.")
    @Nullable
    String keyword,

    @Parameter(description = "Search organization.")
    @Nullable
    String organization,

    @Parameter(description = "Search category. If you want to search for multiple categories, separate them with spaces.")
    @Nullable
    String category,

    @Parameter(description = "Search format. If you want to search for multiple formats, separate them with spaces. Default is unspecified.")
    @Nullable
    String format
) {

    public OpenDataSearcherRequest(
        final Integer page,
        final String keyword,
        final String organization,
        final String category,
        final String format
    ) {
        this.page = Optional.ofNullable(page).orElse(1);
        this.keyword = keyword;
        this.organization = organization;
        this.category = category;
        this.format = format;
    }

    @NonNull
    public Set<String> organizationSet() {

        if (StringUtils.isBlank(organization)) {
            return Set.of();
        }

        return Set.of(organization);
    }

    @NonNull
    public Set<String> categorySet() {
        if (StringUtils.isBlank(category)) {
            return Set.of();
        }

        return Stream.of(category.split(StringUtils.SPACE))
            .collect(Collectors.toUnmodifiableSet());
    }

    @NonNull
    public Set<OpenDataFormat> formatSet() {
        if (StringUtils.isBlank(format)) {
            return Set.of();
        }

        return Stream.of(format.split(StringUtils.SPACE))
            .map(OpenDataFormat::fromString)
            .collect(Collectors.toUnmodifiableSet());
    }
}
