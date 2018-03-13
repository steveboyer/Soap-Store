package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import java.util.ArrayList;


public class LineItem {
//    @Id
    public String id;

    private String name;
    private Integer quantity;

    private Money base_price_money;

    private ArrayList<Discount> discounts;

    private ArrayList<Tax> taxes;

    public LineItem(String name, Integer quantity, Money base_price_money, ArrayList<Discount> discounts, ArrayList<Tax> taxes) {
        this.name = name;
        this.quantity = quantity;
        this.base_price_money = base_price_money;
        this.discounts = discounts;
        this.taxes = taxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Money getBase_price_money() {
        return base_price_money;
    }

    public void setBase_price_money(Money base_price_money) {
        this.base_price_money = base_price_money;
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<Discount> discounts) {
        this.discounts = discounts;
    }

    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<Tax> taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
