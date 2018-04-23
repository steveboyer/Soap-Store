package com.stephenboyer.soapstore.controller;

import com.squareup.connect.models.OrderLineItem;
import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.LineItem;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Scope("request")
public class CartController {

    private final Logger log = LoggerFactory.getLogger(CartController.class.getSimpleName());

    private Cart cart;

    @RequestMapping(value = "/cart", method = GET)
    public ModelAndView cart(){
        ModelAndView mav = new ModelAndView("cart");

        String view = "cart";
        log.info("Hit /cart");
        mav.addObject("view", view );
        mav.addObject("catalog", CatalogFactory.getCatalog());
        mav.addObject("cart", cart);

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

    @RequestMapping(value = "/cart/added_to_cart", method = GET)
    public ModelAndView added_to_cart(@RequestParam String prodId, @RequestParam long quantity){
        try {
            ModelAndView mav = new ModelAndView("added_to_cart");

            // Find product
            SquareConnector sq = new SquareConnector();
            Product product = sq.getProduct(prodId);
//            addItem(product, (int)quantity);
            log.info("Q: " + quantity);
            log.info("P: " + product);
            log.info("Cart: " + cart.toString());

            // Add item to cart
            // Have to find correct ProductVariation
//            cart.addItem(CatalogFactory.findProductVariantBySKU(product, sku), quantity);
//            addItem();
//        System.out.println("s:" + subtotal);//
            mav.addObject("quantity", quantity);
            mav.addObject("product", product);
            mav.addObject("subtotal", 1.00);
            mav.addObject("catalog", CatalogFactory.getCatalog());

            String view = "cart";
            mav.addObject("view", view );

            return mav;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Autowired
    public void setCart(Cart cart){
        this.cart = cart;
    }
}
