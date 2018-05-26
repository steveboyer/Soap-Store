package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.CatalogItemVariation;
import com.stephenboyer.soapstore.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Null;

public class ProductVariation  {
//    @Id
    private String id;

    // Product id for variation is different than for object
    private String variationId;

    // Unique to variation
    private String sku;

    // Variation name
    private String name;
    private Long price;
    private String sPrice;
    private String variationValue;
    private String variationData;
    private String v1VariationId;

    private Boolean available;

    private final transient Logger logger = LoggerFactory.getLogger(ProductVariation.class.getSimpleName());

    public ProductVariation(CatalogItemVariation variation, int totalVariations){
        sku = variation.getSku();
        variationId = variation.getItemId();
        name = variation.getName();
        // @TODO fix NPE below
        try {
            price = variation.getPriceMoney().getAmount();

            // Convert Long value to String, e.g. 700 => $7.00
            sPrice = Strings.getPriceString(price);

            // Cut off third part of sku as variation
            if(sku != null ) {
                String[] variationValues = sku.split("_");
                if(variationValues.length >= 1) {
                    variationValue =
                            variationValues[variationValues.length - 1].substring(0,1).toUpperCase() +
                                    variationValues[variationValues.length - 1]
                                            .substring(1, (variationValues[variationValues.length -1].length()));


                }
            }

            // Goes into html data parameter
            variationData = sku + "/" + sPrice;

        } catch (NullPointerException ex){
            logger.error("Product " + sku + ", " + variationId + " had no price data");
        }
    }

    public ProductVariation(){

    }

    public String getV1VariationId() {
        return v1VariationId;
    }

    public void setV1VariationId(String v1VariationId) {
        this.v1VariationId = v1VariationId;
    }

    public String getVariationData() {
        return variationData;
    }

    public void setVariationData(String variationData) {
        this.variationData = variationData;
    }

    public String getsPrice() {
        return sPrice;
    }

    public void setsPrice(String sPrice) {
        this.sPrice = sPrice;
    }

    public String getVariationValue() {
        return variationValue;
    }

    public void setVariationValue(String variationValue) {
        this.variationValue = variationValue;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}