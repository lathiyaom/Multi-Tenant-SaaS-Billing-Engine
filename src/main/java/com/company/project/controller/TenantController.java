package com.company.project.controller;

import com.company.project.dto.CreateTenantRequest;
import com.company.project.dto.TenantResponse;
import com.company.project.dto.UpdateTenantRequest;
import com.company.project.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.company.project.util.AppConstants.API_V1;

@RestController
@RequestMapping(API_V1 + "/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<TenantResponse> create(@Valid @RequestBody CreateTenantRequest request) {
        TenantResponse response = tenantService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getAll() {
        return ResponseEntity.ok(tenantService.getAll());
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantResponse> getById(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(tenantService.getById(tenantId));
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<TenantResponse> update(@PathVariable UUID tenantId,
                                                 @Valid @RequestBody UpdateTenantRequest request) {
        return ResponseEntity.ok(tenantService.update(tenantId, request));
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<Void> delete(@PathVariable UUID tenantId) {
        tenantService.delete(tenantId);
        return ResponseEntity.noContent().build();
    }
}
