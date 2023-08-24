package com.dxjunkyard.opendata.platform.presentation.dto.response;

import com.dxjunkyard.opendata.platform.domain.model.opendata.DatasetFile;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Builder
public record DatasetFileResponse(
    @NonNull String title,
    @Nullable String format,
    @NonNull String url
) implements Serializable {

    public static DatasetFileResponse from(final DatasetFile datasetFile) {
        return DatasetFileResponse.builder()
            .title(datasetFile.getTitle())
            .format(datasetFile.getFormat())
            .url(datasetFile.getUrl())
            .build();
    }

    public static DatasetFileResponse from(final DatasetFile datasetFile, final String title) {
        return DatasetFileResponse.builder()
            .title(title)
            .format(datasetFile.getFormat())
            .url(datasetFile.getUrl())
            .build();
    }
}
