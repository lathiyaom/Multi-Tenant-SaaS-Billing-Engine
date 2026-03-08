package com.company.project.service;

import com.company.project.dto.CreateTenantRequest;
import com.company.project.dto.TenantResponse;
import com.company.project.dto.UpdateTenantRequest;

import java.util.List;
import java.util.UUID;

public interface TenantService {

    TenantResponse create(CreateTenantRequest request);

    List<TenantResponse> getAll();

    TenantResponse getById(UUID tenantId);

    TenantResponse update(UUID tenantId, UpdateTenantRequest request);

    void delete(UUID tenantId);
}
