package com.dxjunkyard.opendata.platform.infrastructure.dto.factory;

import com.dxjunkyard.opendata.platform.domain.model.opendata.OpenData;
import com.dxjunkyard.opendata.platform.domain.model.search.condition.SearchCondition;
import com.dxjunkyard.opendata.platform.infrastructure.dto.request.opendata.search.OpenDataSearchRequest;
import com.dxjunkyard.opendata.platform.infrastructure.dto.response.opendata.search.OpenDataSearchResponse;
import org.springframework.stereotype.Component;

@Component
public class OpenDataSearchFactory {

    public OpenDataSearchRequest build(final SearchCondition searchCondition) {
        return OpenDataSearchRequest.from(searchCondition);
    }

    public OpenData build(final OpenDataSearchResponse response) {
        return response.toOpenData();
    }
}
