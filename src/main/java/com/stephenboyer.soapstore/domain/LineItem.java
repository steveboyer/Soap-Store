package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

public class LineItem {

    private String productId;
    private Integer quantity;
    private String scent;
    private String size;

    public LineItem() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

}
