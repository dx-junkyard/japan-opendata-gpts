package com.dxjunkyard.opendata.platform.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum OpenDataFormat {
    CSV("CSV"),
    XLS("XLS"),
    PDF("PDF"),
    XLSX("XLSX"),
    JPEG("JPEG"),
    TXT("TXT"),
    ZIP("ZIP"),
    GEO_JSON("GeoJSON"),
    KML("KML"),
    RDF("RDF"),
    HTML("HTML"),
    PNG("PNG"),
    LAS("LAS"),
    DOCX("DOCX"),
    DOC("DOC"),
    JSON("JSON"),
    GIF("GIF"),
    SHP("SHP"),
    MP4("MP4"),
    OBJ("OBJ"),
    XISX("XISX"),
    PLY("PLY"),
    GTFS("GTFS"),
    AI("AI"),
    XML("XML"),
    WORD("WORD"),
    XCSV("XCSV"),
    PPTX("PPTX"),
    ;

    private final String value;

    @NonNull
    public static Set<String> getValues() {
        return Stream.of(OpenDataFormat.values())
            .map(e -> e.value)
            .collect(Collectors.toUnmodifiableSet());
    }

    @NonNull
    public static OpenDataFormat fromString(@NonNull final String format) {
        return Stream.of(OpenDataFormat.values())
            .filter(e -> e.value.equals(format))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("指定されたフォーマットは存在しません。"));
    }
}
