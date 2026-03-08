package com.company.project.service;

import com.company.project.dto.CreatePlanRequest;
import com.company.project.dto.PlanResponse;
import com.company.project.dto.UpdatePlanRequest;

import java.util.List;
import java.util.UUID;

public interface PlanService {

    PlanResponse create(CreatePlanRequest request);

    List<PlanResponse> getByTenant(UUID tenantId);

    PlanResponse getById(UUID planId);

    PlanResponse update(UUID planId, UpdatePlanRequest request);

    void delete(UUID planId);
}
