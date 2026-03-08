package com.company.project.dto;

import com.company.project.entity.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateSubscriptionStatusRequest(
        @NotNull(message = "status is required")
        SubscriptionStatus status
) {
}
