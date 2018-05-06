package com.stephenboyer.soapstore;

import com.stephenboyer.soapstore.controller.IndexController;
import com.stephenboyer.soapstore.domain.Catalog;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;
import com.stephenboyer.soapstore.soap.SquareConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogFactory {
    private static Catalog catalog;

    private static final Logger logger = LoggerFactory.getLogger(CatalogFactory.class.getSimpleName());

    public static Catalog getCatalog(){

        logger.info("Getting catalog" );

        if(catalog == null){
            logger.info("No catalog. Creating one...");

            SquareConnector sq = new SquareConnector();

            catalog = new Catalog(sq.getProducts(), sq.getCategories(), sq.getInventoryEntries());

            return catalog;
        }

        logger.info("Returning outdated catalog...");
        return catalog;
    }




//    public static ProductVariation findProductVariantBySku(String sku){
//        return  getCatalog().findProductVariantBySku();
//    }
}
