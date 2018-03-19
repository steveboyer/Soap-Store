package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CartController {
    @RequestMapping(value = "/store/added", method = GET)
    public ModelAndView added(){
        ModelAndView mav = new ModelAndView("added_to_cart");


        return mav;
    }

    @RequestMapping(value = "/store/cart", method = GET)
    public ModelAndView cart(){
        ModelAndView mav = new ModelAndView("cart");
//        SquareConnector sq = new SquareConnector();
//        sq.getProduct()

        return mav;
    }

    @RequestMapping(value = "/store/continue" , method = GET)
    public ModelAndView continue_shopping(){

    }

    @RequestMapping(value = "/store/added_to_cart", method = GET)
    public ModelAndView added_to_cart(@RequestParam String prodId, @RequestParam long quantity, @RequestParam String size){
        ModelAndView mav = new ModelAndView("added_to_cart");

        // Find product
        SquareConnector sq = new SquareConnector();
        Product product = sq.getProduct(prodId);
        System.out.println("Q: " + quantity);
        System.out.println("P: " + product);
//        System.out.println("s:" + subtotal);//
        mav.addObject("quantity", quantity);
        mav.addObject("product", product);
        mav.addObject("subtotal", 1.00);

        return mav;
    }
}
