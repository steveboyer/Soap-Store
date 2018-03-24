package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.CheckoutRequest;

public interface CheckoutRequestRepository {
    CheckoutRequest create(CheckoutRequest checkoutRequest);
    CheckoutRequest read(String cartRequestId);
    void update(String cartRequestId, CheckoutRequest cartRequest);
    void delete(String cartRequestId);
}
