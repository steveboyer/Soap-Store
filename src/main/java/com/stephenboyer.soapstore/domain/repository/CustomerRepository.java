package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer create(Customer customer);
    Customer read(String customerId);
    void update(String customerId, Customer customer);
    void delete(String customerId);
}
