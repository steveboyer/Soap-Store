package com.stephenboyer.soapstore.services.impl;

import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.repository.CartRepository;
import com.stephenboyer.soapstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
//@Service
public class CartServiceImpl implements CartService {
//    @Autowired
    private CartRepository cartRepository;

    public Cart create(Cart cart){
        return cartRepository.create(cart);
    }

    public Cart read(String cartId){
        return cartRepository.read(cartId);
    }

    public void update(String cartId, Cart cart){
        cartRepository.update(cartId, cart);
    }

    public void delete(String cartId){
        cartRepository.delete(cartId);
    }
}
