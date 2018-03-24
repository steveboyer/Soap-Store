package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.CartItem;

public interface CartItemRepository {
    CartItem create(CartItem cartItem);
    CartItem read(String cartItemId);
    void update(String cartId, CartItem cartItem);
    void delete(String cartId);
}
