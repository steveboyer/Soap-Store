package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.CatalogItemVariation;
import com.stephenboyer.soapstore.util.Strings;

public class ProductVariation  {
//    @Id
    private String id;

    // Product id for variation is different than for object
    private String variationId;

    // Unique to variation
    private String sku;

    // Variation name
    private String name;
    private long price;


    public ProductVariation(CatalogItemVariation variation){
        sku = variation.getSku();
        variationId = variation.getItemId();
        name = variation.getName();
        // @TODO fix NPE below
        //price = variation.getPriceMoney().getAmount();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}