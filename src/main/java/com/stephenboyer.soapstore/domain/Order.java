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
    List<LineItem> line_items;

    public Order(){



    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}