package com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.search;

import com.dxjunkyard.opendata.platform.domain.model.opendata.Dataset;
import com.dxjunkyard.opendata.platform.domain.model.opendata.DatasetFile;
import com.dxjunkyard.opendata.platform.domain.model.opendata.OpenData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenDataSearchResponse(
    SearchResultResponse result
) {

    @NonNull
    public OpenData toOpenData() {
        return OpenData.builder()
            .total(result.count())
            .dataset(result.toDataset())
            .build();
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchResultResponse(
        Integer count,
        List<ResultResponse> results
    ) {
        public List<Dataset> toDataset() {
            return results.stream()
                .map(ResultResponse::toDataset)
                .toList();
        }
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResultResponse(
        String xckanTitle,
        String xckanSiteName,
        String xckanSiteUrl,
        String xckanDescription,
        String type,
        String metadataModified,
        String metadataCreated,
        List<ResourceResponse> resources
    ) {
        @NonNull
        public Dataset toDataset() {
            return Dataset.builder()
                .title(xckanTitle)
                .description(Optional.ofNullable(xckanDescription).filter(StringUtils::isNotBlank).orElse(null))
                .datasetUrl(Optional.ofNullable(xckanSiteUrl).filter(StringUtils::isNotBlank).orElse(null))
                .files(resources.stream()
                    .map(ResourceResponse::toDatasetFile)
                    .toList())
                .build();
        }
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResourceResponse(
        String id,
        String name,
        String format,
        String url,
        String downloadUrl,
        String updated,
        String created
    ) {

        public DatasetFile toDatasetFile() {

            final var url = Optional.ofNullable(downloadUrl)
                .filter(StringUtils::isNotBlank)
                .orElse(this.url);

            return DatasetFile.builder()
                .title(name)
                .format(format)
                .lastModified(Optional.ofNullable(updated)
                    .map(datetime -> LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME))
                    .orElse(null))
                .url(url)
                .build();
        }
    }
}
