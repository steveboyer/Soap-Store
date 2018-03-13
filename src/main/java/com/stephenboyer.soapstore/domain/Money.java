package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import java.io.Serializable;

public class Money implements Serializable {
//    @Id
    public String id;

    private long amount;
    private String currency;


    public Money(long amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}