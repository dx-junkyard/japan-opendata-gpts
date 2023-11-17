package com.dxjunkyard.opendata.platform.presentation.dto.request;

import com.dxjunkyard.opendata.platform.domain.model.Language;
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

    @Parameter(description = "Search area. Please enter the geographical area you are interested in. You can specify a region, city, or prefecture.")
    @Nullable
    String area,

    @Parameter(description = "Search keyword. If you want to search for multiple keywords, separate them with spaces.")
    @Nullable
    String keyword,

    @Parameter(description = "Search format. If you want to search for multiple formats, separate them with spaces. Default is unspecified.")
    @Nullable
    String format,

    @Parameter(description = "Asked language. select only JAPANESE or OTHER. Default is Japanese.", required = true)
    @NonNull Language language
) {

    public OpenDataSearcherRequest(
        final Integer page,
        final String area,
        final String keyword,
        final String format,
        final Language language
    ) {
        this.page = Optional.ofNullable(page).orElse(1);
        this.area = area;
        this.keyword = keyword;
        this.format = format;
        this.language = language;
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
