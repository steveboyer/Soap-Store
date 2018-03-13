package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

import java.io.Serializable;

public class CheckoutRequest implements Serializable {
//    @Id
    public String id;

    String redirect_url;
    String indempotency_key;
    Boolean ask_ful_shipping_address;
    String support_email_address;




   public CheckoutRequest(){


   }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
