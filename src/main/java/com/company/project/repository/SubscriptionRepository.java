package com.company.project.repository;

import com.company.project.entity.Subscription;
import com.company.project.entity.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    List<Subscription> findByTenantId(UUID tenantId);

    List<Subscription> findByTenantIdAndStatus(UUID tenantId, SubscriptionStatus status);
}
