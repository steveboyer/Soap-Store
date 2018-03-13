package com.stephenboyer.soapstore.controller;

import org.springframework.web.bind.annotation.RestController;


@RestController
// View-less requests
// Rest API, called via AJAX
public class CartControllerRest {
//    private final CartRepository repository;

//    @Autowired
//    private CartRepository cartRepository;

//    @RequestMapping(value = "/store/item/add", method = POST,
//                    consumes = MediaType.APPLICATION_JSON_VALUE,
//                    produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Cart> addToCart(@RequestBody CartItem cartItem) throws IOException, JsonMappingException, JsonParseException {
//        System.out.println("Add to cart clicked");
//
//
//
//
////        try {
////            // Look up cart for customer
////            Cart cart = mongo.getCart(cartItem.getCartId());
////
////            System.out.println(cartItem.toString());
////
////            cart.addItem(cartItem);
////
////            return new ResponseEntity<>(cart, HttpStatus.OK);
////        } catch (NullPointerException ex) {
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//    }
}
