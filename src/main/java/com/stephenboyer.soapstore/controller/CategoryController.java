package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;
//import com.stephenboyer.soapstore.soap.SquareConnector;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CategoryController {
    @RequestMapping(value = "/store/category/{categoryId}", method = GET)
    ModelAndView category(@PathVariable String categoryId){

        ModelAndView mav = new ModelAndView("category");
        SquareConnector sq = new SquareConnector();

        Category current = sq.getCategory(categoryId);
        List<Product> products = sq.getProductsInCategory(current);
        List<Category> categories = sq.getCategories();

        mav.addObject("categories", categories);
        mav.addObject("products", products);
        mav.addObject("category", current);



        return mav;
    }


}
