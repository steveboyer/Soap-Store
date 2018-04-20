package com.stephenboyer.soapstore.soap;

/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Author: SquareConnector
 * Revision: Steve Boyer
 */


import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.*;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.*;
import com.squareup.connect.models.Address;
import com.stephenboyer.soapstore.controller.IndexController;
import com.stephenboyer.soapstore.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class SquareConnector {
    //private static Logger log = LogManager.getLogger(SquareConnector.class.getSimpleName());

    // The environment variable containing a SquareConnector Personal Access Token.
    // This must be set in order for the application to start.
    private static final String SQUARE_ACCESS_TOKEN = loadEnvironmentVariable("ACCESS_TOKEN");

    // The environment variable containing a SquareConnector application ID.
    // This must be set in order for the application to start.
    private static final String SQUARE_APP_ID = loadEnvironmentVariable("APPLICATION_ID");

    //public static final String BASE_URL = "https://connect.squareup.com";

    private static ApiClient squareClient;
    private Location squareLocation;
    private static final Logger logger = LoggerFactory.getLogger(SquareConnector.class.getSimpleName());

    public ApiClient getSquareClient() {

        if(squareClient == null) {
            squareClient = Configuration.getDefaultApiClient();

            // Configure OAuth2 access token for authorization: oauth2
            OAuth oauth2 = (OAuth) squareClient.getAuthentication("oauth2");
            oauth2.setAccessToken(SQUARE_ACCESS_TOKEN); //mustLoadEnvironmentVariable(SQUARE_ACCESS_TOKEN_ENV_VAR));
        }

        return squareClient;
    }

    public SquareConnector() {
        squareClient = getSquareClient();
    }

    public CatalogObject getCatalogObject(String id){

        CatalogApi api = new CatalogApi();
        api.setApiClient(getSquareClient());

        try {
            RetrieveCatalogObjectResponse response =  api.retrieveCatalogObject(id, false);

            return response.getObject();
        } catch (ApiException ex){
            ex.printStackTrace();
            return null;
        }

    }

    public List<Category> getCategories(){
        try {
            V1ItemsApi v1ItemsApi = new V1ItemsApi();

            getSquareLocation();

            String locationId = squareLocation.getId();

            List<V1Category> result = v1ItemsApi.listCategories(locationId);
            List<Category> categories = new ArrayList<>();

            result.forEach(r -> categories.add(new Category(r.getName(), r.getId())));

            return categories;
        } catch (ApiException | NullPointerException ex){
            System.err.println("Could not get categories.");
            return null;
        }
    }

    public void checkout(){

        CheckoutApi api = new CheckoutApi();


        ArrayList<CreateOrderRequestLineItem> lineItems = new ArrayList<>();

        lineItems.add( new CreateOrderRequestLineItem()
                .name("Printed T Shirt")
                .quantity("2")
                .basePriceMoney(new Money()
                        .amount(1500L)
                        .currency(Money.CurrencyEnum.USD))
                .addDiscountsItem(new CreateOrderRequestDiscount()
                        .name("7% off previous season item")
                        .percentage("7")
                )
                .addDiscountsItem(new CreateOrderRequestDiscount()
                        .name("$3 off Customer Discount")
                        .amountMoney(new Money()
                                .amount(300L)
                                .currency(Money.CurrencyEnum.USD))
                ));

        lineItems.add(new CreateOrderRequestLineItem()
                .name("Slim Jeans")
                .quantity("1")
                .basePriceMoney(new Money()
                        .amount(2500L)
                        .currency(Money.CurrencyEnum.USD))
        );

        Address address = new Address()
                .addressLine1("1455 Market St.")
                .addressLine2("Suite 600")
                .locality("San Francisco")
                .administrativeDistrictLevel1("CA")
                .postalCode("94103")
                .country(Address.CountryEnum.US);


        try {
            CreateCheckoutResponse response = api.createCheckout(getSquareLocation().getId(), new CreateCheckoutRequest()
                    .idempotencyKey(UUID.randomUUID().toString())
                    .order(new CreateOrderRequest()
                            .referenceId("reference_id")
                            .lineItems(lineItems)
                            .discounts(Arrays.asList(new CreateOrderRequestDiscount().name("Father's day 12% OFF").percentage("12"),
                                    new CreateOrderRequestDiscount()
                                            .name("Global Sales $55 OFF")
                                            .amountMoney(new Money().amount(5500L).currency(Money.CurrencyEnum.USD))))
                            .addTaxesItem(new CreateOrderRequestTax().name("Sales Tax").percentage("8.5")
                            )
                    )
                    .askForShippingAddress(true)
                    .merchantSupportEmail("merchant+support@website.com")
                    .prePopulateBuyerEmail("example@email.com")
                    .prePopulateShippingAddress(address)
                    .redirectUrl("https://merchant.website.com/order-confirm"));

            Checkout checkout = response.getCheckout();

            checkout.getOrder().getLineItems().stream().forEach(p -> logger.info(p.getName() + " " + p.getBasePriceMoney().getAmount()));
        } catch (ApiException ex){
            logger.error(ex.getMessage());
        }

    }

    public Product getProduct(String id){
        return new Product(getCatalogObject(id));
    }

    public Location getSquareLocation(){
        if(squareLocation == null){
            // Get location and
            // check if login was successful
            try {
                squareLocation = lookupCardProcessingLocation();
            } catch (ApiException ex){
                System.err.println("LOGIN ERROR");
                return null;
            }
        }

        return squareLocation;
    }

    // Get the first [number] of products in the square catalog
    // number: number of products to return;
    private List<CatalogObject> getSquareCatalog(int limit) {

        CatalogApi api = new CatalogApi();
        api.setApiClient(getSquareClient());

        // List all category and text objects
        // Returned a page at a time
        String cursor = "";
        String types = String.format("%s", CatalogObject.TypeEnum.ITEM);

        // Store all results in an list.
        List<CatalogObject> objects = new ArrayList<>();
        do {
            ListCatalogResponse response;
            try {
                response = api.listCatalog(cursor, types);
            } catch (ApiException ex){
                ex.printStackTrace();
                return null;
            }

            if (response.getObjects() != null) {
                objects.addAll(response.getObjects());
            }
            cursor = response.getCursor();
            // When response.cursor is nil, the results are complete.

        } while(cursor != null );

        // 0 means list all
        if(limit != 0) {
            return objects.subList(0, limit);
        }
        return objects;
    }

    // Retrieve entire catalog
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        // Add CatalogObjects as Products to List
        // Null check
        Objects.requireNonNull(getSquareCatalog(0)).forEach(p -> products.add(new Product(p)));

        return products;
    }

    public static String loadEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.length() == 0) {
            throw new IllegalStateException(
                    String.format("The %s environment variable must be set", name));
        }

        return value;
    }

    public Location lookupCardProcessingLocation() throws ApiException {
        LocationsApi locationsApi = new LocationsApi();
        locationsApi.setApiClient(getSquareClient());

        try {
            Location location = locationsApi.listLocations().getLocations().stream()
                    .filter(l -> l.getCapabilities().contains(Location.CapabilitiesEnum.PROCESSING))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(
                            "At least one location must support card processing"));
            //System.out.println(location.getAddress());
            return location;
        } catch (NullPointerException ex){
            // Location does not have ability to process payments
            System.err.println("Could not get a valid location.");
            return null;
        }
    }

    public String charge(NonceForm nonceForm) throws ApiException {
        // To learn more about splitting transactions with additional recipients,
        // see the Transactions API documentation on our [developer site]
        // (https://docs.connect.squareup.com/payments/transactions/overview#mpt-overview).
        ChargeRequest chargeRequest = new ChargeRequest()
                .idempotencyKey(UUID.randomUUID().toString())
                .amountMoney(new Money().amount(1_00L).currency(Money.CurrencyEnum.USD))
                .cardNonce(nonceForm.getNonce())
                .note("From a Square sample Java app");

        TransactionsApi transactionsApi = new TransactionsApi();
        transactionsApi.setApiClient(squareClient);

        ChargeResponse response = transactionsApi.charge(squareLocation.getId(), chargeRequest);

        return response.getTransaction().getId();


    }
}
