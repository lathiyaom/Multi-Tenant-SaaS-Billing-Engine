package com.company.project.service;

import com.company.project.dto.CreatePlanRequest;
import com.company.project.dto.PlanResponse;
import com.company.project.dto.UpdatePlanRequest;
import com.company.project.entity.Plan;
import com.company.project.entity.Tenant;
import com.company.project.exception.DuplicateResourceException;
import com.company.project.exception.PlanNotFoundException;
import com.company.project.exception.TenantNotFoundException;
import com.company.project.mapper.PlanMapper;
import com.company.project.repository.PlanRepository;
import com.company.project.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {

    private static final Logger log = LoggerFactory.getLogger(PlanServiceImpl.class);

    private final PlanRepository planRepository;
    private final TenantRepository tenantRepository;
    private final PlanMapper planMapper;

    @Override
    public PlanResponse create(CreatePlanRequest request) {
        Tenant tenant = tenantRepository.findById(request.tenantId())
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found: " + request.tenantId()));

        if (planRepository.existsByTenantIdAndName(request.tenantId(), request.name().trim())) {
            throw new DuplicateResourceException("Plan name already exists for tenant: " + request.name());
        }

        Plan plan = planMapper.toEntity(request, tenant);
        Plan saved = planRepository.save(plan);

        log.info("Plan created with id {} for tenant {}", saved.getId(), request.tenantId());
        return planMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanResponse> getByTenant(UUID tenantId) {
        return planRepository.findByTenantId(tenantId).stream()
                .map(planMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlanResponse getById(UUID planId) {
        return planMapper.toResponse(findPlanById(planId));
    }

    @Override
    public PlanResponse update(UUID planId, UpdatePlanRequest request) {
        Plan plan = findPlanById(planId);

        if (!plan.getName().equals(request.name().trim())
                && planRepository.existsByTenantIdAndName(plan.getTenant().getId(), request.name().trim())) {
            throw new DuplicateResourceException("Plan name already exists for tenant: " + request.name());
        }

        planMapper.updateEntity(plan, request);
        Plan updated = planRepository.save(plan);

        log.info("Plan updated with id {}", planId);
        return planMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID planId) {
        Plan plan = findPlanById(planId);
        planRepository.delete(plan);

        log.info("Plan deleted with id {}", planId);
    }

    private Plan findPlanById(UUID planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan not found: " + planId));
    }
}
