package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Order;

public interface OrderRepository {
    Order create(Order order);
    Order read(String orderId);
    void update(String orderId, Order order);
    void delete(String orderId);
}
