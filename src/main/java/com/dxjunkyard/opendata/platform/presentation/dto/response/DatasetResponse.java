package com.dxjunkyard.opendata.platform.presentation.dto.response;

import com.dxjunkyard.opendata.platform.domain.model.opendata.Dataset;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Builder
public record DatasetResponse(
    @Nullable String title,
    @NonNull String siteName,
    @Nullable String datasetUrl,
    @Nullable String license,
    @NonNull List<DatasetFileResponse> files,
    @NonNull List<String> tags
) implements Serializable {

    public static DatasetResponse from(final Dataset dataset, @Nullable final List<DatasetFileResponse> overriddenFiles, @Nullable final String titleEn) {
        // 上書き用のオープンデータがあればそれを使う
        final var filesResponse = Objects.nonNull(overriddenFiles)
            ? overriddenFiles
            : dataset.getFiles().stream().map(DatasetFileResponse::from).toList();

        return DatasetResponse.builder()
            .title(StringUtils.isNoneBlank(titleEn) ? titleEn : dataset.getTitle())
            .siteName(dataset.getSiteName())
            .datasetUrl(dataset.getDatasetUrl())
            .license(dataset.getLicense())
            .files(filesResponse)
            .tags(dataset.getTags())
            .build();
    }

}
