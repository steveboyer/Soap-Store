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
import com.squareup.connect.api.CatalogApi;
import com.squareup.connect.api.LocationsApi;
import com.squareup.connect.api.V1ItemsApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.*;
import com.stephenboyer.soapstore.domain.Category;
import com.stephenboyer.soapstore.domain.Product;

import java.util.ArrayList;
import java.util.List;


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
    public List<CatalogObject> getSquareCatalog(int limit) {

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

    // Map product to category by id
    public Product mapProduct(List<Category> categories, Product product){

        // Iterate through and set category for each product
        for(Category cat : categories){
            if(product.getCategoryId() == null) continue;
            if(cat == null) continue;

            if(product.getCategoryId().equals(cat.getId())){
                product.setCategory(cat);
                break;
            }
        }

        return product;
    }

    // Retrieve entire catalog
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        List<Category> categories = getCategories();

        // Get catalog, create product, map category to product, add to list
        getSquareCatalog(0).forEach(p -> products.add(mapProduct(categories, new Product(p))));
        products.stream().forEach(p -> System.out.println(p));

        return products;
    }

    // Get p products from catalog (category not set)
    public List<Product> getProducts(int number){
        List<Product> products = new ArrayList<>();
        getSquareCatalog(number).forEach(p -> products.add(new Product(p)));
        products.stream().forEach(p -> System.out.println(p));
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

//    @RequestMapping("/square")
//    String index(Map<String, Object> model) throws ApiException {
//        model.put("locationId", squareLocation.getId());
//        model.put("locationName", squareLocation.getName());
//        model.put("addId", squareAppId);
//
//        return "index";
//    }
//
//    @PostMapping("/square/charge")
//    String charge(@ModelAttribute NonceForm form, Map<String, Object> model) throws ApiException {
//        // To learn more about splitting transactions with additional recipients,
//        // see the Transactions API documentation on our [developer site]
//        // (https://docs.connect.squareup.com/payments/transactions/overview#mpt-overview).
//        ChargeRequest chargeRequest = new ChargeRequest()
//                .idempotencyKey(UUID.randomUUID().toString())
//                .amountMoney(new Money().amount(1_00L).currency(CurrencyEnum.USD))
//                .cardNonce(form.getNonce())
//                .note("From a SquareConnector sample Java app");
//
//        TransactionsApi transactionsApi = new TransactionsApi();
//        transactionsApi.setApiClient(squareClient);
//
//        ChargeResponse response = transactionsApi.charge(squareLocation.getId(), chargeRequest);
//
//        model.put("transactionId", response.getTransaction().getId());
//
//        return "charge";
//    }

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

    public Category getCategory(String id){
        for(Category category : getCategories()){
            if(id.equals(category.getId())){
                return category;
            }
        }

        return null;
    }

    /**
     * Get products matching @param category
     * @param category
     * @return
     */
    public List<Product> getProductsInCategory(Category category){
        List<Product> products = new ArrayList<>();

        List<Product> allProducts = getProducts();


        for(Product product : allProducts){

            if(product.getCategory() != null && product.getCategory().getName().equals(category.getName())){
                products.add(product);
            }
        }

        products.forEach(p -> System.out.println(p.getName()));
        return products;


        // Filter list to get products in category
        //products = getProducts().stream().filter(p -> p.getCategory().getName().equals(category.getName())).collect(Collectors.toList());

    }
}
