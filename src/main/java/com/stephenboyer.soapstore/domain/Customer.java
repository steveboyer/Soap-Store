package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

public class Customer {
    //    @Id
    public String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    String pre_populate_buyer_email;

    @Override
    public String toString() {
        return Strings.toString(this);
    }

}
