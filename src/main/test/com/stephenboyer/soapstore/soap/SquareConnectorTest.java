package com.stephenboyer.soapstore.soap;

import com.stephenboyer.soapstore.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareConnectorTest {
    SquareConnector squareConnector = new SquareConnector();

    @Test
    void getSquareClient() {
    }

    @Test
    void getCatalogObject() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void getSquareLocation() {
    }

    @Test
    void getSquareCatalog() {
    }

    @Test
    void mapProduct() {
    }

    @Test
    void getProducts() {
        List<Product> products = squareConnector.getProducts();

        products.stream().forEach(p -> System.out.println(p));
    }

    @Test
    void getProducts1() {
    }

    @Test
    void mustLoadEnvironmentVariable() {
    }

    @Test
    void lookupCardProcessingLocation() {
    }

    @Test
    void getCategories() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getProductsInCategory() {
    }
}