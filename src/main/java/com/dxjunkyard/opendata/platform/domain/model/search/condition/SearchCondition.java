package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import com.dxjunkyard.opendata.platform.domain.model.Language;
import com.dxjunkyard.opendata.platform.domain.model.OpenDataFormat;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Builder
public class SearchCondition {

    @NonNull
    private final Integer page;

    @NonNull
    private final AreaSearchCondition areaSearchCondition;

    @NonNull
    private final KeywordSearchCondition keywordSearchCondition;

    @NonNull
    private final Set<OpenDataFormat> formatSet;

    @NonNull
    private final Language language;

    @Nullable
    public String getRawArea() {
        return areaSearchCondition.getRaw();
    }

    @Nullable
    public String getRawKeyword() {
        return keywordSearchCondition.getRaw();
    }

    @NonNull
    public String getArea() {
        return areaSearchCondition.getArea();
    }

    public boolean isJapanese() {
        return Language.JAPANESE == language;
    }

    public boolean existsKeyword() {
        return CollectionUtils.isNotEmpty(getAllQuerySet());
    }

    public Set<String> getAllQuerySet() {
        return keywordSearchCondition.getKeywordSet();
    }

    public String getAllQuery() {
        return String.join(" ", getAllQuerySet());
    }

    @Nullable
    public String getFormat() {

        if (formatSet.isEmpty()) {
            return null;
        }

        return String.join(StringUtils.SPACE, formatSet.stream()
            .map(OpenDataFormat::getValue)
            .collect(Collectors.toUnmodifiableSet()));
    }
}
