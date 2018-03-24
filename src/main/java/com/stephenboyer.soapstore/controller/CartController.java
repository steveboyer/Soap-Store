package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @RequestMapping(value = "/", method = GET)
    public ModelAndView cart(){
        ModelAndView mav = new ModelAndView("cart");

        return mav;
    }

    @RequestMapping(value = "/store/continue" , method = GET)
    public String continue_shopping(){
        return "redirect:/";
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
