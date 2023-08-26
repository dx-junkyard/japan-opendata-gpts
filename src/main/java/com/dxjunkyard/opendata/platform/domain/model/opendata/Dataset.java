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
    protected final String title;
    @Nullable
    protected final String description;
    @Nullable
    protected final String datasetUrl;
    @Nullable
    protected final String license;
    @NonNull
    protected final List<DatasetFile> files;
}
