package com.stephenboyer.soapstore;

import com.stephenboyer.soapstore.domain.Catalog;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.domain.ProductVariation;

public class CatalogHolder {
    private static Catalog catalog;

    public static Catalog getCatalog(){
        if(catalog == null){
            return new Catalog();
        }

        return catalog;
    }

//    public static ProductVariation findProductVariantBySku(String sku){
//        return  getCatalog().findProductVariantBySku();
//    }
}
