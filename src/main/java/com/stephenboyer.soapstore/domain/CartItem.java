package com.stephenboyer.soapstore.domain;


import com.stephenboyer.soapstore.util.Strings;

//
//@Entity
//@Table(name = "cartitem")
public class CartItem {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;

    private String productId;
    private Integer quantity;
    private String scent;
    private String size;

    public CartItem(){

    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
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
    public String toString(){
        return Strings.toString(this);
    }

}
