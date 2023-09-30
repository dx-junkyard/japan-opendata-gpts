package com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.opendata.japan;

import com.dxjunkyard.opendata.platform.infrastructure.dto.request.opendata.search.OpenDataSearchRequest;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.category.OpenDataCategoryResponse;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.organization.OpenDataOrganizationResponse;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.search.OpenDataSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JapanOpenDataRepository {

    private final WebClient japanOpenDataClient;

    @NonNull
    public Mono<OpenDataSearchResponse> search(final OpenDataSearchRequest request) {

        final Map<String, Object> searchRequestParameterMap = new HashMap<>();

        searchRequestParameterMap.put("rows", request.rows());
        searchRequestParameterMap.put("start", request.start());
        Optional.ofNullable(request.q())
            .ifPresent(q -> searchRequestParameterMap.put("q", q));
        Optional.ofNullable(request.fq())
            .ifPresent(fq -> searchRequestParameterMap.put("fq", fq));

        return japanOpenDataClient.get()
            .uri(uriBuilder -> {
                searchRequestParameterMap.keySet()
                    .forEach(value -> uriBuilder.queryParam(value, "{" + value + "}"));
                return uriBuilder.build(searchRequestParameterMap);
            })
            .retrieve()
            .bodyToMono(OpenDataSearchResponse.class);
    }

    @NonNull
    public Mono<OpenDataOrganizationResponse> fetchArea() {

        final Map<String, Object> areaRequestParameterMap = Map.of(
            "rows", 1
        );

        return japanOpenDataClient.get()
            .uri(uriBuilder -> {
                areaRequestParameterMap.keySet()
                    .forEach(value -> uriBuilder.queryParam(value, "{" + value + "}"));
                return uriBuilder.build(areaRequestParameterMap);
            })
            .retrieve()
            .bodyToMono(OpenDataOrganizationResponse.class);
    }

    @NonNull
    public Mono<OpenDataCategoryResponse> fetchCategory() {

        final Map<String, Object> categoryRequestParameterMap = Map.of(
            "rows", 1
        );

        return japanOpenDataClient.get()
            .uri(uriBuilder -> {
                categoryRequestParameterMap.keySet()
                    .forEach(value -> uriBuilder.queryParam(value, "{" + value + "}"));
                return uriBuilder.build(categoryRequestParameterMap);
            })
            .retrieve()
            .bodyToMono(OpenDataCategoryResponse.class);
    }
}
