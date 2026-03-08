package com.company.project.service;

import com.company.project.dto.CreateSubscriptionRequest;
import com.company.project.dto.SubscriptionResponse;
import com.company.project.dto.UpdateSubscriptionStatusRequest;
import com.company.project.entity.Plan;
import com.company.project.entity.Subscription;
import com.company.project.entity.SubscriptionStatus;
import com.company.project.entity.Tenant;
import com.company.project.exception.PlanNotFoundException;
import com.company.project.exception.SubscriptionNotFoundException;
import com.company.project.exception.TenantNotFoundException;
import com.company.project.mapper.SubscriptionMapper;
import com.company.project.repository.PlanRepository;
import com.company.project.repository.SubscriptionRepository;
import com.company.project.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionRepository subscriptionRepository;
    private final TenantRepository tenantRepository;
    private final PlanRepository planRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponse create(CreateSubscriptionRequest request) {
        Tenant tenant = tenantRepository.findById(request.tenantId())
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found: " + request.tenantId()));

        Plan plan = planRepository.findById(request.planId())
                .orElseThrow(() -> new PlanNotFoundException("Plan not found: " + request.planId()));

        if (!plan.getTenant().getId().equals(tenant.getId())) {
            throw new PlanNotFoundException("Plan does not belong to tenant: " + request.planId());
        }

        LocalDate startDate = LocalDate.now();
        LocalDate renewalDate = startDate.plusMonths(1);
        LocalDate trialEnd = plan.getTrialDays() > 0 ? startDate.plusDays(plan.getTrialDays()) : null;
        SubscriptionStatus initialStatus = plan.getTrialDays() > 0 ? SubscriptionStatus.TRIAL : SubscriptionStatus.ACTIVE;

        Subscription subscription = Subscription.builder()
                .tenant(tenant)
                .plan(plan)
                .customerId(request.customerId())
                .status(initialStatus)
                .startDate(startDate)
                .renewalDate(renewalDate)
                .trialEnd(trialEnd)
                .build();

        Subscription saved = subscriptionRepository.save(subscription);
        log.info("Subscription created with id {} for tenant {}", saved.getId(), request.tenantId());

        return subscriptionMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getByTenant(UUID tenantId) {
        return subscriptionRepository.findByTenantId(tenantId).stream()
                .map(subscriptionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse getById(UUID subscriptionId) {
        return subscriptionMapper.toResponse(findById(subscriptionId));
    }

    @Override
    public SubscriptionResponse updateStatus(UUID subscriptionId, UpdateSubscriptionStatusRequest request) {
        Subscription subscription = findById(subscriptionId);
        subscription.setStatus(request.status());

        if (request.status() == SubscriptionStatus.CANCELLED && subscription.getCancelledAt() == null) {
            subscription.setCancelledAt(LocalDate.now());
        }

        Subscription updated = subscriptionRepository.save(subscription);
        log.info("Subscription status updated for id {} to {}", subscriptionId, request.status());

        return subscriptionMapper.toResponse(updated);
    }

    @Override
    public SubscriptionResponse cancel(UUID subscriptionId) {
        Subscription subscription = findById(subscriptionId);
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        if (subscription.getCancelledAt() == null) {
            subscription.setCancelledAt(LocalDate.now());
        }

        Subscription updated = subscriptionRepository.save(subscription);
        log.info("Subscription cancelled for id {}", subscriptionId);

        return subscriptionMapper.toResponse(updated);
    }

    private Subscription findById(UUID subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found: " + subscriptionId));
    }
}
