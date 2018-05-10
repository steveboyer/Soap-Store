package com.stephenboyer.soapstore.controller;

import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.LocationsApi;
import com.squareup.connect.api.TransactionsApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.ChargeRequest;
import com.squareup.connect.models.ChargeResponse;
import com.squareup.connect.models.Location;
import com.squareup.connect.models.Money;
import com.stephenboyer.soapstore.domain.NonceForm;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.UUID;

public class SquareController {


    @PostMapping("/charge")
    String charge(@ModelAttribute NonceForm form, Map<String, Object> model) throws ApiException {
//        SquareConnector sq = new SquareConnector();
//        products = sq.getProducts(0);
//        categories = sq.getCategories()


        SquareConnector squareConnector = new SquareConnector();

        TransactionsApi transactionsApi = new TransactionsApi();

        NonceForm nonceForm = new NonceForm();


        String responseId = squareConnector.charge(nonceForm);




        model.put("transactionId", responseId);

        return "charge";
    }
}