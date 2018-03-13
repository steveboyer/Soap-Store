package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.CatalogItemVariation;
import com.stephenboyer.soapstore.util.Strings;

public class ProductVariation  {
//    @Id
    public String id;

    // Product id for variation is different than for object
    String variationId;

    // Unique to variation
    String sku;

    // Variation name
    String name;
    long price;

    public ProductVariation(CatalogItemVariation variation){
        sku = variation.getSku();
        variationId = variation.getItemId();
        name = variation.getName();
        price = variation.getPriceMoney().getAmount();
    }
    @Override
    public String toString() {
        return Strings.toString(this);
    }
}