package com.company.project.mapper;

import com.company.project.dto.CreateTenantRequest;
import com.company.project.dto.TenantResponse;
import com.company.project.dto.UpdateTenantRequest;
import com.company.project.entity.Tenant;
import com.company.project.entity.TenantStatus;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    public Tenant toEntity(CreateTenantRequest request) {
        return Tenant.builder()
                .name(request.name().trim())
                .slug(request.slug().trim())
                .billingEmail(request.billingEmail().trim())
                .status(TenantStatus.ACTIVE)
                .build();
    }

    public TenantResponse toResponse(Tenant tenant) {
        return TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .slug(tenant.getSlug())
                .billingEmail(tenant.getBillingEmail())
                .status(tenant.getStatus())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
    }

    public void updateEntity(Tenant tenant, UpdateTenantRequest request) {
        tenant.setName(request.name().trim());
        tenant.setSlug(request.slug().trim());
        tenant.setBillingEmail(request.billingEmail().trim());
        tenant.setStatus(request.status());
    }
}
