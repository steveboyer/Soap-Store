package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import java.io.Serializable;

public class Discount implements Serializable {

//    @Id
    public String id;

    private String name;
    private Money money;

    public Discount(String name, Money money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
