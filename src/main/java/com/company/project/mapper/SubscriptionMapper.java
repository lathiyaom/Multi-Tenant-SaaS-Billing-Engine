package com.company.project.mapper;

import com.company.project.dto.SubscriptionResponse;
import com.company.project.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionResponse toResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .tenantId(subscription.getTenant().getId())
                .planId(subscription.getPlan().getId())
                .customerId(subscription.getCustomerId())
                .status(subscription.getStatus())
                .startDate(subscription.getStartDate())
                .renewalDate(subscription.getRenewalDate())
                .trialEnd(subscription.getTrialEnd())
                .cancelledAt(subscription.getCancelledAt())
                .createdAt(subscription.getCreatedAt())
                .updatedAt(subscription.getUpdatedAt())
                .build();
    }
}
