package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.CheckoutRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CheckoutRequestRepositoryImpl {
    private Map<String, CheckoutRequest> checkoutRequestes;

    public CheckoutRequestRepositoryImpl(){
        checkoutRequestes = new HashMap<>();
    }

    public CheckoutRequest create(CheckoutRequest checkoutRequest){
        if(checkoutRequestes.keySet().contains(checkoutRequest.getId())) {
            throw new IllegalArgumentException(String.format("CheckoutRequest %s already exists", checkoutRequest.getId()));
        }
        checkoutRequestes.put(checkoutRequest.getId(), checkoutRequest);
        return checkoutRequest;
    }

    public CheckoutRequest read(String cartId){
        return checkoutRequestes.get(cartId);
    }

    public void update(String checkoutRequestId, CheckoutRequest checkoutRequest){
        if(!checkoutRequestes.keySet().contains(checkoutRequestId)){
            throw new IllegalArgumentException(String.format("Cannot update because checkoutRequest %s does not exist", checkoutRequest.getId()));
        }

        else checkoutRequestes.put(checkoutRequestId, checkoutRequest);
    }

    public void delete(String checkoutRequestId){
        if(!checkoutRequestes.keySet().contains(checkoutRequestId)){
            throw new IllegalArgumentException(String.format("Cannot update because checkoutRequest %s does not exist", checkoutRequestId));
        }
        checkoutRequestes.remove(checkoutRequestId);
    }
}
