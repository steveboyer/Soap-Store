package com.stephenboyer.soapstore.services;

import com.stephenboyer.soapstore.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
