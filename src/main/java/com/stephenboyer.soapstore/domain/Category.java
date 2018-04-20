package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;

public class Category {

    public String id;

    private String name;
    private String url;
    private String categoryId;

    public Category(String name, String cid) {
        this.name = name;
        this.id = name.toLowerCase().replace(" ", "-");
        this.url = "/store/category/" + id;
        this.categoryId = cid;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
