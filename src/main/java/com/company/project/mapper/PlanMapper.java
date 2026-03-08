package com.company.project.mapper;

import com.company.project.dto.CreatePlanRequest;
import com.company.project.dto.PlanResponse;
import com.company.project.dto.UpdatePlanRequest;
import com.company.project.entity.Plan;
import com.company.project.entity.PlanStatus;
import com.company.project.entity.Tenant;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public Plan toEntity(CreatePlanRequest request, Tenant tenant) {
        return Plan.builder()
                .tenant(tenant)
                .name(request.name().trim())
                .monthlyPrice(request.monthlyPrice())
                .yearlyPrice(request.yearlyPrice())
                .currency(request.currency().trim().toUpperCase())
                .trialDays(request.trialDays())
                .apiCallLimit(request.apiCallLimit())
                .storageMbLimit(request.storageMbLimit())
                .status(PlanStatus.ACTIVE)
                .build();
    }

    public void updateEntity(Plan plan, UpdatePlanRequest request) {
        plan.setName(request.name().trim());
        plan.setMonthlyPrice(request.monthlyPrice());
        plan.setYearlyPrice(request.yearlyPrice());
        plan.setCurrency(request.currency().trim().toUpperCase());
        plan.setTrialDays(request.trialDays());
        plan.setApiCallLimit(request.apiCallLimit());
        plan.setStorageMbLimit(request.storageMbLimit());
        plan.setStatus(request.status());
    }

    public PlanResponse toResponse(Plan plan) {
        return PlanResponse.builder()
                .id(plan.getId())
                .tenantId(plan.getTenant().getId())
                .name(plan.getName())
                .monthlyPrice(plan.getMonthlyPrice())
                .yearlyPrice(plan.getYearlyPrice())
                .currency(plan.getCurrency())
                .trialDays(plan.getTrialDays())
                .apiCallLimit(plan.getApiCallLimit())
                .storageMbLimit(plan.getStorageMbLimit())
                .status(plan.getStatus())
                .createdAt(plan.getCreatedAt())
                .updatedAt(plan.getUpdatedAt())
                .build();
    }
}
