package com.company.project.controller;

import com.company.project.dto.CreatePlanRequest;
import com.company.project.dto.PlanResponse;
import com.company.project.dto.UpdatePlanRequest;
import com.company.project.service.PlanService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.company.project.util.AppConstants.API_V1;

@RestController
@RequestMapping(API_V1 + "/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponse> create(@Valid @RequestBody CreatePlanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getByTenant(@RequestParam UUID tenantId) {
        return ResponseEntity.ok(planService.getByTenant(tenantId));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponse> getById(@PathVariable UUID planId) {
        return ResponseEntity.ok(planService.getById(planId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponse> update(@PathVariable UUID planId,
                                               @Valid @RequestBody UpdatePlanRequest request) {
        return ResponseEntity.ok(planService.update(planId, request));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> delete(@PathVariable UUID planId) {
        planService.delete(planId);
        return ResponseEntity.noContent().build();
    }
}
