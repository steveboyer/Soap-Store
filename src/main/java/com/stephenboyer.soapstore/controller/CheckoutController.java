package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogHolder;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CheckoutController {
//    // Homepage
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView index(Model model) {
//        ModelAndView mav = new ModelAndView("index");
//
//        SquareConnector sq = new SquareConnector();
//        List<Product> products = sq.getProducts();
//        mav.addObject("products", products);
//
//        return mav;
//    }

    @RequestMapping(value="/store/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(){
        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("catalog", CatalogHolder.getCatalog());
        //mav.addObject("")

        return mav;
    }
}
