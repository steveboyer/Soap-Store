package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Cart;
import com.stephenboyer.soapstore.domain.Catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Scope("request")
public class IndexController {


//    private Cart cart;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class.getSimpleName());
    // Returns robots.txt via robots.html
    @RequestMapping(value = "/robots.txt", method = GET)
    public String robots(HttpServletRequest request) {
        return "robots";
    }

    // Homepage
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        logger.info("/ hit");
        Catalog catalog = CatalogFactory.getCatalog();

        ModelAndView mav = new ModelAndView("index");

        logger.info("view set");

        mav.addObject("catalog", catalog);

        logger.info("catalog added");


//        Cart cart = (Cart) session.getAttribute("cart");
//        logger.info(cart.toString());



        String view = "home";
        mav.addObject("view", view );

        return mav;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView contact(){
        ModelAndView mav = new ModelAndView("contact");

        mav.addObject("catalog", CatalogFactory.getCatalog());


        String view = "contact";
        mav.addObject("view", view);
        return mav;
    }

    @RequestMapping(value = "/about")
    public ModelAndView about(){
        ModelAndView mav = new ModelAndView("about");
        mav.addObject("catalog", CatalogFactory.getCatalog());
        String view = "about";
        mav.addObject("view", view);
        return mav;
    }

//    @Autowired
//    public void setCart(Cart cart){
//        this.cart = cart;
//    }

}
