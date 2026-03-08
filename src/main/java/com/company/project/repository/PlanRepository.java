package com.company.project.repository;

import com.company.project.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {

    List<Plan> findByTenantId(UUID tenantId);

    boolean existsByTenantIdAndName(UUID tenantId, String name);
}
