package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogHolder;
import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ProductController {

    // Product page
    @RequestMapping(value = "/store/item/{id}", method = GET)
    public ModelAndView product(Model model, @PathVariable String id) {
        ModelAndView mav = new ModelAndView("product");

        SquareConnector sq = new SquareConnector();

//        List<Product> products = sq.getProducts(); // All products
//        List<Category> categories = sq.getCategories(); // All categories
        Product product = sq.getProduct(id); // Product on this page

        mav.addObject("catalog", CatalogHolder.getCatalog());
//        mav.addObject("products", products);

        // Get product as defined in URL


        // Get list of products and set variables for Thymeleaf
        mav.addObject("product", product);
        System.out.println(product);
        List<ProductVariation> variations = product.getProductVariations();

        // List
        mav.addObject("related", 0);
        System.out.println(product);
        return mav;
    }
}
