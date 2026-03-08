package com.company.project.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSubscriptionRequest(
        @NotNull(message = "tenantId is required")
        UUID tenantId,

        @NotNull(message = "planId is required")
        UUID planId,

        @NotNull(message = "customerId is required")
        UUID customerId
) {
}
