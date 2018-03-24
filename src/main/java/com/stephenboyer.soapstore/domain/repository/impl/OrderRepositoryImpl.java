package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl {
    private Map<String, Address> addresses;

    public OrderRepositoryImpl(){
        addresses = new HashMap<>();
    }

    public Address create(Address address){
        if(addresses.keySet().contains(address.getId())) {
            throw new IllegalArgumentException(String.format("Address %s already exists", address.getId()));
        }
        addresses.put(address.getId(), address);
        return address;
    }

    public Address read(String cartId){
        return addresses.get(cartId);
    }

    public void update(String addressId, Address address){
        if(!addresses.keySet().contains(addressId)){
            throw new IllegalArgumentException(String.format("Cannot update because address %s does not exist", address.getId()));
        }

        else addresses.put(addressId, address);
    }

    public void delete(String addressId){
        if(!addresses.keySet().contains(addressId)){
            throw new IllegalArgumentException(String.format("Cannot update because address %s does not exist", addressId));
        }
        addresses.remove(addressId);
    }
}
