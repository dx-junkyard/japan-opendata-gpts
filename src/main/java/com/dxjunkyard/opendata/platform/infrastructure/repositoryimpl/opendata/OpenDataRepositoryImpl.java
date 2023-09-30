package com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.opendata;

import com.dxjunkyard.opendata.platform.domain.model.opendata.OpenData;
import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import com.dxjunkyard.opendata.platform.domain.repository.opendata.OpenDataRepository;
import com.dxjunkyard.opendata.platform.infrastructure.dto.factory.OpenDataSearchFactory;
import com.dxjunkyard.opendata.platform.infrastructure.dto.request.opendata.search.OpenDataSearchRequest;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.category.OpenDataCategoryResponse;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.organization.OpenDataOrganizationResponse;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.search.OpenDataSearchResponse;
import com.dxjunkyard.opendata.platform.infrastructure.repositoryimpl.opendata.japan.JapanOpenDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OpenDataRepositoryImpl implements OpenDataRepository {

    private final JapanOpenDataRepository japanOpenDataRepository;

    private final OpenDataSearchFactory openDataSearchFactory;

    @Override
    @NonNull
    public Mono<OpenData> search(final SearchCondition searchCondition) {

        final OpenDataSearchRequest request = openDataSearchFactory.build(searchCondition);

        final Mono<OpenDataSearchResponse> response = japanOpenDataRepository.search(request);

        return response.map(openDataSearchFactory::build);
    }

    @Override
    @NonNull
    public Mono<OrganizationNameToIdConverter> fetchOrganization() {
        return japanOpenDataRepository.fetchArea()
            .map(OpenDataOrganizationResponse::convertOrganization)
            .onErrorResume(throwable -> {
                log.error("組織情報の取得に失敗しました。", throwable);
                return Mono.just(OrganizationNameToIdConverter.empty());
            });
    }

    @Override
    @NonNull
    public Mono<CategoryNameToIdConverter> fetchCategory() {
        return japanOpenDataRepository.fetchCategory()
            .map(OpenDataCategoryResponse::convertCategory)
            .onErrorResume(throwable -> {
                log.error("カテゴリ情報の取得に失敗しました。", throwable);
                return Mono.just(CategoryNameToIdConverter.empty());
            });
    }
}
