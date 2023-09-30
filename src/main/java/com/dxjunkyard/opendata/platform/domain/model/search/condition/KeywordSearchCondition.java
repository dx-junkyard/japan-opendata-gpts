package com.dxjunkyard.opendata.platform.domain.model.search.condition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class KeywordSearchCondition {

    @NonNull
    private final Set<String> keywordSet;

    @Getter
    @NonNull
    private final String raw;

    @NonNull
    public static KeywordSearchCondition of(final String queries) {
        return new KeywordSearchCondition(Stream.of(StringUtils
                .split(StringUtils.defaultString(queries), StringUtils.SPACE))
            .collect(Collectors.toUnmodifiableSet()), queries);
    }

    @NonNull
    Set<String> getKeywordSet() {
        return keywordSet.stream().map(query -> "\"" + query + "\"").collect(Collectors.toUnmodifiableSet());
    }
}
