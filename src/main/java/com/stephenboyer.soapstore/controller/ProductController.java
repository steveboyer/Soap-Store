package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class.getName());
    // Product page
    @RequestMapping(value = "/store/item/{id}", method = GET)
    public ModelAndView product(Model model, @PathVariable String id) {
        ModelAndView mav = new ModelAndView("product");

        SquareConnector sq = new SquareConnector();

//        List<Product> products = sq.getProducts(); // All products
//        List<Category> categories = sq.getCategories(); // All categories
        Product product = sq.getProduct(id); // Product on this page

        mav.addObject("catalog", CatalogFactory.getCatalog());
//        mav.addObject("products", products);

        // Get product as defined in URL


        // Get list of products and set variables for Thymeleaf
        mav.addObject("product", product);
        log.info(product.toString());
        List<ProductVariation> variations = product.getProductVariations();

        // List
        mav.addObject("related", 0);


        String view = "home";
        mav.addObject("view", view );

        return mav;
    }
}
