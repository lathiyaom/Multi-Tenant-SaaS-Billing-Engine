package com.company.project.service;

import com.company.project.dto.CreateTenantRequest;
import com.company.project.dto.TenantResponse;
import com.company.project.dto.UpdateTenantRequest;
import com.company.project.entity.Tenant;
import com.company.project.exception.DuplicateTenantException;
import com.company.project.exception.TenantNotFoundException;
import com.company.project.mapper.TenantMapper;
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
public class TenantServiceImpl implements TenantService {

    private static final Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantResponse create(CreateTenantRequest request) {
        if (tenantRepository.existsByName(request.name().trim())) {
            throw new DuplicateTenantException("Tenant name already exists: " + request.name());
        }
        if (tenantRepository.existsBySlug(request.slug().trim())) {
            throw new DuplicateTenantException("Tenant slug already exists: " + request.slug());
        }

        Tenant tenant = tenantMapper.toEntity(request);
        Tenant saved = tenantRepository.save(tenant);

        log.info("Tenant created with id {} and slug {}", saved.getId(), saved.getSlug());
        return tenantMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenantResponse> getAll() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TenantResponse getById(UUID tenantId) {
        Tenant tenant = findTenantById(tenantId);
        return tenantMapper.toResponse(tenant);
    }

    @Override
    public TenantResponse update(UUID tenantId, UpdateTenantRequest request) {
        Tenant tenant = findTenantById(tenantId);

        String normalizedName = request.name().trim();
        String normalizedSlug = request.slug().trim();

        if (!tenant.getName().equals(normalizedName) && tenantRepository.existsByName(normalizedName)) {
            throw new DuplicateTenantException("Tenant name already exists: " + normalizedName);
        }
        if (!tenant.getSlug().equals(normalizedSlug) && tenantRepository.existsBySlug(normalizedSlug)) {
            throw new DuplicateTenantException("Tenant slug already exists: " + normalizedSlug);
        }

        tenantMapper.updateEntity(tenant, request);
        Tenant updated = tenantRepository.save(tenant);

        log.info("Tenant updated with id {}", updated.getId());
        return tenantMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID tenantId) {
        Tenant tenant = findTenantById(tenantId);
        tenantRepository.delete(tenant);

        log.info("Tenant deleted with id {}", tenantId);
    }

    private Tenant findTenantById(UUID tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found: " + tenantId));
    }
}
