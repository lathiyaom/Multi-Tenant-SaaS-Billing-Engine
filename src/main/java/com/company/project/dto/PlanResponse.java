package com.company.project.dto;

import com.company.project.entity.PlanStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record PlanResponse(
        UUID id,
        UUID tenantId,
        String name,
        BigDecimal monthlyPrice,
        BigDecimal yearlyPrice,
        String currency,
        Integer trialDays,
        Integer apiCallLimit,
        Integer storageMbLimit,
        PlanStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
