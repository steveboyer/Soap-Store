package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl {
    private Map<String, Customer> customeres;

    public CustomerRepositoryImpl(){
        customeres = new HashMap<>();
    }

    public Customer create(Customer customer){
        if(customeres.keySet().contains(customer.getId())) {
            throw new IllegalArgumentException(String.format("Customer %s already exists", customer.getId()));
        }
        customeres.put(customer.getId(), customer);
        return customer;
    }

    public Customer read(String cartId){
        return customeres.get(cartId);
    }

    public void update(String customerId, Customer customer){
        if(!customeres.keySet().contains(customerId)){
            throw new IllegalArgumentException(String.format("Cannot update because customer %s does not exist", customer.getId()));
        }

        else customeres.put(customerId, customer);
    }

    public void delete(String customerId){
        if(!customeres.keySet().contains(customerId)){
            throw new IllegalArgumentException(String.format("Cannot update because customer %s does not exist", customerId));
        }
        customeres.remove(customerId);
    }
}
