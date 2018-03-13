package com.stephenboyer.soapstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        ModelAndView mav = new ModelAndView("added_to_cart");


        return mav;
    }


}
