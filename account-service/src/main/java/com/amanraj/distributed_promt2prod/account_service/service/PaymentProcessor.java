package com.amanraj.distributed_promt2prod.account_service.service;


import com.amanraj.distributed_promt2prod.account_service.dto.subscription.CheckoutRequest;
import com.amanraj.distributed_promt2prod.account_service.dto.subscription.CheckoutResponse;
import com.amanraj.distributed_promt2prod.account_service.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutResponse(CheckoutRequest request);

    PortalResponse openCustomerPortal();

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
