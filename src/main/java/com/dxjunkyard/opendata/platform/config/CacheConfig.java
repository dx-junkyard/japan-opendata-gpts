package com.dxjunkyard.opendata.platform.config;

import com.dxjunkyard.opendata.platform.domain.model.search.CategoryNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.model.search.OrganizationNameToIdConverter;
import com.dxjunkyard.opendata.platform.domain.repository.opendata.OpenDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.Optional;

@Configuration
public class CacheConfig {
    @Bean
    @NonNull
    public CategoryNameToIdConverter categoryNameToIdConverter(final OpenDataRepository openDataRepository) {
        final var category =  openDataRepository.fetchCategory().block();
        return Optional.ofNullable(category)
            .orElseGet(CategoryNameToIdConverter::empty);
    }

    @Bean
    @NonNull
    public OrganizationNameToIdConverter organizationNameToIdConverter(final OpenDataRepository openDataRepository) {
        final var organization = openDataRepository.fetchOrganization().block();
        return Optional.ofNullable(organization)
            .orElseGet(OrganizationNameToIdConverter::empty);
    }
}
