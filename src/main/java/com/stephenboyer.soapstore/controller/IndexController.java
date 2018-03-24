package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    // Returns robots.txt via robots.html
    @RequestMapping(value = "/robots.txt", method = GET)
    public String robots(HttpServletRequest request) {
        return "robots";
    }

    // Homepage
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        SquareConnector sq = new SquareConnector();
        List<Product> products = sq.getProducts(9);
        List<Category> categories = sq.getCategories();

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("categories", categories);
        mav.addObject("products", products);

        return mav;
    }
}
