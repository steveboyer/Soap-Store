package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import java.util.List;

public class Order{
//    @Id
    public String id;

    String redirect_url;
    String indempotency_key;
    Boolean ask_for_shipping_address;
    String merchant_support_email;

    String reference_id;

    public Order(){



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
}
