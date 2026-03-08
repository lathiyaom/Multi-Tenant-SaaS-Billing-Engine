package com.company.project.service;

import com.company.project.dto.CreateSubscriptionRequest;
import com.company.project.dto.SubscriptionResponse;
import com.company.project.dto.UpdateSubscriptionStatusRequest;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {

    SubscriptionResponse create(CreateSubscriptionRequest request);

    List<SubscriptionResponse> getByTenant(UUID tenantId);

    SubscriptionResponse getById(UUID subscriptionId);

    SubscriptionResponse updateStatus(UUID subscriptionId, UpdateSubscriptionStatusRequest request);

    SubscriptionResponse cancel(UUID subscriptionId);
}
