package com.company.project.dto;

import com.company.project.entity.TenantStatus;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record TenantResponse(
        UUID id,
        String name,
        String slug,
        String billingEmail,
        TenantStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
