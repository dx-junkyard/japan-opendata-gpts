package com.dxjunkyard.opendata.platform.domain.model.opendata;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class OpenData {
    @NonNull
    private final Integer total;

    @NonNull
    private final List<Dataset> dataset;
}
