package com.company.project.dto;

import com.company.project.entity.TenantStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateTenantRequest(
        @NotBlank(message = "name is required")
        @Size(max = 150, message = "name must be at most 150 characters")
        String name,

        @NotBlank(message = "slug is required")
        @Size(max = 150, message = "slug must be at most 150 characters")
        @Pattern(regexp = "^[a-z0-9-]+$", message = "slug must contain lowercase letters, numbers, and hyphen")
        String slug,

        @NotBlank(message = "billingEmail is required")
        @Email(message = "billingEmail must be valid")
        @Size(max = 200, message = "billingEmail must be at most 200 characters")
        String billingEmail,

        @NotNull(message = "status is required")
        TenantStatus status
) {
}
