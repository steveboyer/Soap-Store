package com.stephenboyer.soapstore.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
// View-less requests
// Rest API, called via AJAX
public class CartControllerRest {
//    private final CartRepository repository;

    @RequestMapping(value = "/store/item/add", method = POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addToCart(@RequestBody CartItem cartItem) throws IOException, JsonMappingException, JsonParseException {
        System.out.println("Add to cart clicked");

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


//        try {
//            // Look up cart for customer
//            Cart cart = mongo.getCart(cartItem.getCartId());
//
//            System.out.println(cartItem.toString());
//
//            cart.addItem(cartItem);
//
//            return new ResponseEntity<>(cart, HttpStatus.OK);
//        } catch (NullPointerException ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
