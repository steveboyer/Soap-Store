package com.stephenboyer.soapstore.domain;


import com.stephenboyer.soapstore.util.Strings;

public class Address {
//    @Id
    public String id;

    String address_line_1;
    String address_line_2;
    String locality;
    String administrative_district_level_1;
    String postal_code;
    String country;
    String first_name;
    String last_name;

    public Address(String address_line_1, String address_line_2, String locality, String administrative_district_level_1, String postal_code, String country, String first_name, String last_name) {

        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.locality = locality;
        this.administrative_district_level_1 = administrative_district_level_1;
        this.postal_code = postal_code;
        this.country = country;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdministrative_district_level_1() {
        return administrative_district_level_1;
    }

    public void setAdministrative_district_level_1(String administrative_district_level_1) {
        this.administrative_district_level_1 = administrative_district_level_1;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
