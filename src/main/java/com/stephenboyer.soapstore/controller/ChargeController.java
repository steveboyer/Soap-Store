package com.stephenboyer.soapstore.controller;

import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.TransactionsApi;
import com.squareup.connect.models.ChargeRequest;
import com.squareup.connect.models.ChargeResponse;
import com.squareup.connect.models.Money;
import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.NonceForm;
import com.stephenboyer.soapstore.domain.Product;
//import com.stephenboyer.soapstore.soap.SquareConnector;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller

public class ChargeController {

//    @RequestMapping(value = "/store/category/{categoryId}", method = GET)
//    ModelAndView category(@PathVariable String categoryId){
//
//        ModelAndView mav = new ModelAndView("category");
//        SquareConnector sq = new SquareConnector();
//
////        String current = CatalogFactory.getCatalog().getCategory(categoryName);
//        List<Product> products = CatalogFactory.getCatalog().getProductsInCategory(categoryId);
//        List<Category> categories = sq.getCategories();
//
////        mav.addObject("categories", categories);
//        mav.addObject("catalog", CatalogFactory.getCatalog());
//        mav.addObject("products", products);
//        mav.addObject("category", categoryId);
//        mav.addObject("catname", CatalogFactory.getCatalog().getCategory(categoryId).getName());
//        // getCategoryById and send name
//
//
////        mav.addObject("categories", categories);
//
//        String view = "home";
//        mav.addObject("view", view );
//
//        return mav;
//    }


    @RequestMapping(value = "/charge", method = POST)
    String charge(@ModelAttribute NonceForm form, Map<String, Object> model) throws ApiException {
        SquareConnector squareConnector = new SquareConnector();


        ChargeRequest chargeRequest = new ChargeRequest()
                .idempotencyKey(UUID.randomUUID().toString())
                .amountMoney((new Money()).amount(1_00L).currency(Money.CurrencyEnum.USD))
                .cardNonce(form.getNonce())
                .note("Test");

        TransactionsApi transactionsApi = new TransactionsApi();
        transactionsApi.setApiClient(Configuration.getDefaultApiClient());

        ChargeResponse response = transactionsApi.charge(squareConnector.getSquareLocation().getId(), chargeRequest);

        model.put("transactionId", response.getTransaction().getId());

        return "charge";
    }


}