package com.dxjunkyard.opendata.platform.domain.service;

import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UrlBuilderDomainService {

    private static final String CATALOG_BASE_URL = "https://search.ckan.jp/";

    @NonNull
    public String build(final SearchCondition searchCondition) {

        final Map<String, Object> map = new HashMap<>();

        final var builder = UriComponentsBuilder.fromHttpUrl(CATALOG_BASE_URL);

        final String q = Stream.of(searchCondition.getArea(), searchCondition.getAllQuery())
            .filter(StringUtils::isNotBlank)
            .map(e -> String.format("(%s)", e))
            .collect(Collectors.joining(" AND "));

        Optional.of(q)
            .filter(StringUtils::isNotBlank)
            .ifPresent(keyword -> {
                map.put("q", keyword);
                builder.queryParam("q", "{q}");
            });

        searchCondition.getFormatSet()
            .forEach(format -> {
                map.put("res_format_" + format.getValue(), "format:" + format.getValue());
                builder.queryParam("fq", "{res_format_" + format.getValue() + "}");
            });

        return builder.build(map).toString();
    }
}
