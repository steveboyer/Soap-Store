package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogHolder;
import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    private final Logger log = LoggerFactory.getLogger(CartController.class.getSimpleName());

    @Inject
    private Cart cart;


    @RequestMapping(value = "/cart", method = GET)
    public ModelAndView cart(){
        ModelAndView mav = new ModelAndView("cart");

        String view = "cart";
        mav.addObject("view", view );
        mav.addObject("catalog", CatalogHolder.getCatalog());
        return mav;
    }

    @RequestMapping(value = "/continue" , method = GET)
    public String continue_shopping(){
        return "redirect:/";
    }

    public void addItem(ProductVariation product, int quantity){
        HashMap<ProductVariation, Integer> products = cart.getLineItems();

        Integer currentQuantity = products.getOrDefault(product, 0);

        products.put(product, quantity + currentQuantity);

        log.info(cart.toString());
    }

    @RequestMapping(value = "/added_to_cart", method = GET)
    public ModelAndView added_to_cart(@RequestParam String prodId, @RequestParam long quantity, @RequestParam(value = "size", required = false, defaultValue = "") String sku){
        try {
            ModelAndView mav = new ModelAndView("added_to_cart");

            // Find product
            SquareConnector sq = new SquareConnector();
            Product product = sq.getProduct(prodId);
            System.out.println("Q: " + quantity);
            System.out.println("P: " + product);
            System.out.println("S: " + sku);


            // Add item to cart
            // Have to find correct ProductVariation
//            cart.addItem(CatalogHolder.findProductVariantBySKU(product, sku), quantity);
//            addItem();
//        System.out.println("s:" + subtotal);//
            mav.addObject("quantity", quantity);
            mav.addObject("product", product);
            mav.addObject("subtotal", 1.00);
            mav.addObject("catalog", CatalogHolder.getCatalog());




            log.info(cart.toString());

            String view = "cart";
            mav.addObject("view", view );


            return mav;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
