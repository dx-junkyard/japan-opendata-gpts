package com.dxjunkyard.opendata.platform.domain.repository.opendata;

import com.dxjunkyard.opendata.platform.domain.model.opendata.OpenData;
import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import reactor.core.publisher.Mono;

public interface OpenDataRepository {

    Mono<OpenData> search(SearchCondition searchCondition);

    Mono<OrganizationNameToIdConverter> fetchOrganization();

    Mono<CategoryNameToIdConverter> fetchCategory();
}
