package com.dxjunkyard.opendata.platform.domain.model.opendata;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class Dataset {

    @NonNull
    private final String title;
    @NonNull
    private final String siteName;
    @NonNull
    private final String lastModified;
    @Nullable
    private final String description;
    @Nullable
    private final String datasetUrl;
    @Nullable
    private final String license;
    @NonNull
    private final List<DatasetFile> files;
}
