package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl {
    private Map<String, Cart> carts;

    public CartRepositoryImpl(){
        carts = new HashMap<>();
    }

    public Cart create(Cart cart){
        if(carts.keySet().contains(cart.getId())) {
            throw new IllegalArgumentException(String.format("Cart %s already exists", cart.getId()));
        }
        carts.put(cart.getId(), cart);
        return cart;
    }

    public Cart read(String cartId){
        return carts.get(cartId);
    }

    public void update(String cartId, Cart cart){
        if(!carts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cannot update because cart %s does not exist", cart.getId()));
        }

        else carts.put(cartId, cart);
    }

    public void delete(String cartId){
        if(!carts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cannot update because cart %s does not exist", cartId));
        }
        carts.remove(cartId);
    }
}
