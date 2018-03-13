package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

public class Tax {
//    @Id
    public String id;

    private String name;
    private double percentage;
    private String type;

    public Tax(String name, double percentage, String type) {
        this.name = name;
        this.percentage = percentage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
