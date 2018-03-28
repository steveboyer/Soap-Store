package com.stephenboyer.soapstore;

import com.stephenboyer.soapstore.domain.Catalog;

public class CatalogHolder {
    private static Catalog catalog;

    public static Catalog getCatalog(){
        if(catalog == null){
            return new Catalog();
        }

        return catalog;
    }
}
