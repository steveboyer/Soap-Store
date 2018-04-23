package com.stephenboyer.soapstore.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.squareup.connect.models.OrderLineItem;
import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.CartItem;
import com.stephenboyer.soapstore.domain.LineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@Scope("request")
public class CartControllerRest {

    private final Logger log = LoggerFactory.getLogger(CartControllerRest.class.getSimpleName());

    private Cart cart;

    @RequestMapping(value = "/cart/add", method = POST)
    public ResponseEntity<Cart> addToCart(@RequestBody(required = false) LineItem lineItem){

        log.info(lineItem.toString());

//        cart.addItem();

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @Autowired
    public void setCart(Cart cart){
        this.cart = cart;
    }
}
