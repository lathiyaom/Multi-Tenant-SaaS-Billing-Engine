package com.company.project.dto;

import com.company.project.entity.SubscriptionStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record SubscriptionResponse(
        UUID id,
        UUID tenantId,
        UUID planId,
        UUID customerId,
        SubscriptionStatus status,
        LocalDate startDate,
        LocalDate renewalDate,
        LocalDate trialEnd,
        LocalDate cancelledAt,
        Instant createdAt,
        Instant updatedAt
) {
}
