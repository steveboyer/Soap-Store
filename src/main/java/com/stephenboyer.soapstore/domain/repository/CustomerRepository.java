package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Customer;

public interface CustomerRepository {
    Customer create(Customer customer);
    Customer read(String customerId);
    void update(String customerId, Customer customer);
    void delete(String customerId);
}
