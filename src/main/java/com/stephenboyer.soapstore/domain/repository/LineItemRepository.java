package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.LineItem;

public interface LineItemRepository {
    LineItem create(LineItem lineItem);
    LineItem read(String lineItemId);
    void update(String lineItemId, LineItem lineItem);
    void delete(String lineItemId);
}
