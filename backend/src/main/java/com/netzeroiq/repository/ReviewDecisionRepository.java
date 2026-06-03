package com.netzeroiq.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netzeroiq.model.ReviewDecision;
import com.netzeroiq.model.Tenant;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewDecisionRepository extends JpaRepository<ReviewDecision, UUID> {
    List<ReviewDecision> findByRecordTenantOrderByDecidedAtDesc(Tenant tenant);
}
