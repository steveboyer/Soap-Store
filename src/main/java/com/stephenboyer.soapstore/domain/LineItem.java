package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import javax.sound.sampled.Line;

public class LineItem {

    // Name of overall product including variations
    private String productName;

    // Id of product including all variations
    private String productId;

    // sku of specific variation
    private String productSku;


    private Integer quantity;
    private Long total;
    private Long price;

    public LineItem(){

    }

    public LineItem(String productSku, Integer quantity){
        this.productSku = productSku;
        this.quantity = quantity;
    }

    public LineItem(String productId, String sku, String productName, Integer quantity) {
        this.productId = productId;
        this.productSku = sku;
        this.productName = productName;
        this.quantity = quantity;

        // Find productvariation
        //



    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof LineItem)){
            return false;
        }

        LineItem item = (LineItem) o;

        return item.productSku.equals(getProductSku());

    }
}
