package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class.getSimpleName());

    @RequestMapping(value = "/store/category/{categoryId}", method = GET)
    ModelAndView category(@PathVariable String categoryId){
        logger.info("categoryId: " + categoryId);
        ModelAndView mav = new ModelAndView("category");
        SquareConnector sq = new SquareConnector();

//        String current = CatalogFactory.getCatalog().getCategory(categoryName);
        List<Product> products = CatalogFactory.getCatalog().getProductsInCategory(categoryId);

//        mav.addObject("categories", categories);
        mav.addObject("catalog", CatalogFactory.getCatalog());
        mav.addObject("products", products);
        mav.addObject("category", categoryId);
        mav.addObject("catname", CatalogFactory.getCatalog().getCategory(categoryId).getName());
        // getCategoryById and send name

//        mav.addObject("categories", categories);

        String view = "home";
        mav.addObject("view", view );

        return mav;
    }


}
