package com.company.project.dto;

import com.company.project.entity.PlanStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdatePlanRequest(
        @NotBlank(message = "name is required")
        @Size(max = 100, message = "name must be at most 100 characters")
        String name,

        @NotNull(message = "monthlyPrice is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "monthlyPrice must be greater than 0")
        BigDecimal monthlyPrice,

        @NotNull(message = "yearlyPrice is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "yearlyPrice must be greater than 0")
        BigDecimal yearlyPrice,

        @NotBlank(message = "currency is required")
        @Size(max = 10, message = "currency must be at most 10 characters")
        String currency,

        @NotNull(message = "trialDays is required")
        @Min(value = 0, message = "trialDays must be at least 0")
        @Max(value = 90, message = "trialDays must be at most 90")
        Integer trialDays,

        @NotNull(message = "apiCallLimit is required")
        @Min(value = 1, message = "apiCallLimit must be at least 1")
        Integer apiCallLimit,

        @NotNull(message = "storageMbLimit is required")
        @Min(value = 1, message = "storageMbLimit must be at least 1")
        Integer storageMbLimit,

        @NotNull(message = "status is required")
        PlanStatus status
) {
}
