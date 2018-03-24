package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl {
    private Map<String, Order> orderes;

    public OrderRepositoryImpl(){
        orderes = new HashMap<>();
    }

    public Order create(Order order){
        if(orderes.keySet().contains(order.getId())) {
            throw new IllegalArgumentException(String.format("Order %s already exists", order.getId()));
        }
        orderes.put(order.getId(), order);
        return order;
    }

    public Order read(String cartId){
        return orderes.get(cartId);
    }

    public void update(String orderId, Order order){
        if(!orderes.keySet().contains(orderId)){
            throw new IllegalArgumentException(String.format("Cannot update because order %s does not exist", order.getId()));
        }

        else orderes.put(orderId, order);
    }

    public void delete(String orderId){
        if(!orderes.keySet().contains(orderId)){
            throw new IllegalArgumentException(String.format("Cannot update because order %s does not exist", orderId));
        }
        orderes.remove(orderId);
    }
}
