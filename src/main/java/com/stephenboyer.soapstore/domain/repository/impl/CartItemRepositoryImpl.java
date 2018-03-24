package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.CartItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartItemRepositoryImpl {
    private Map<String, CartItem> cartItemes;

    public CartItemRepositoryImpl(){
        cartItemes = new HashMap<>();
    }

    public CartItem create(CartItem cartItem){
        if(cartItemes.keySet().contains(cartItem.getId())) {
            throw new IllegalArgumentException(String.format("CartItem %s already exists", cartItem.getId()));
        }
        cartItemes.put(cartItem.getId(), cartItem);
        return cartItem;
    }

    public CartItem read(String cartId){
        return cartItemes.get(cartId);
    }

    public void update(String cartItemId, CartItem cartItem){
        if(!cartItemes.keySet().contains(cartItemId)){
            throw new IllegalArgumentException(String.format("Cannot update because cartItem %s does not exist", cartItem.getId()));
        }

        else cartItemes.put(cartItemId, cartItem);
    }

    public void delete(String cartItemId){
        if(!cartItemes.keySet().contains(cartItemId)){
            throw new IllegalArgumentException(String.format("Cannot update because cartItem %s does not exist", cartItemId));
        }
        cartItemes.remove(cartItemId);
    }
}
