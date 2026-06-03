package com.netzeroiq.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netzeroiq.model.DataSource;
import com.netzeroiq.model.Tenant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, UUID> {
    List<DataSource> findByTenant(Tenant tenant);
    Optional<DataSource> findByIdAndTenantAndSourceType(UUID id, Tenant tenant, DataSource.SourceType sourceType);
}
