package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

public class Category {
//    @Id
    public String id;

    private String name;
    private String url;
    private String categoryId;

    public Category(String name, String id) {
        this.name = name;
        this.id = id;
        this.url = "/store/category/" + id;
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
