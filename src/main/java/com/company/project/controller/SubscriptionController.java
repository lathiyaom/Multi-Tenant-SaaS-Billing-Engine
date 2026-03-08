package com.company.project.controller;

import com.company.project.dto.CreateSubscriptionRequest;
import com.company.project.dto.SubscriptionResponse;
import com.company.project.dto.UpdateSubscriptionStatusRequest;
import com.company.project.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(API_V1 + "/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(@Valid @RequestBody CreateSubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getByTenant(@RequestParam UUID tenantId) {
        return ResponseEntity.ok(subscriptionService.getByTenant(tenantId));
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> getById(@PathVariable UUID subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getById(subscriptionId));
    }

    @PutMapping("/{subscriptionId}/status")
    public ResponseEntity<SubscriptionResponse> updateStatus(@PathVariable UUID subscriptionId,
                                                             @Valid @RequestBody UpdateSubscriptionStatusRequest request) {
        return ResponseEntity.ok(subscriptionService.updateStatus(subscriptionId, request));
    }

    @PostMapping("/{subscriptionId}/cancel")
    public ResponseEntity<SubscriptionResponse> cancel(@PathVariable UUID subscriptionId) {
        return ResponseEntity.ok(subscriptionService.cancel(subscriptionId));
    }
}
