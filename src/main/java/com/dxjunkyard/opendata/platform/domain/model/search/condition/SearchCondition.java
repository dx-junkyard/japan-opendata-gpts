package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import com.dxjunkyard.opendata.platform.domain.model.Language;
import com.dxjunkyard.opendata.platform.domain.model.OpenDataFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
@Builder
public class SearchCondition {

    @NonNull
    private final Integer page;

    @NonNull
    private final KeywordSearchCondition keywordSearchCondition;

    @NonNull
    private final Set<OpenDataFormat> formatSet;

    @NonNull
    private final Language language;

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
        return String.join(" AND ", getAllQuerySet());
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
