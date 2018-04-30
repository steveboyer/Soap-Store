package com.stephenboyer.soapstore.util;

import com.stephenboyer.soapstore.domain.Catalog;
import com.stephenboyer.soapstore.domain.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getImageComingSoon() && !o2.getImageComingSoon()) return 1;

        if(!o1.getImageComingSoon() && o2.getImageComingSoon()) return -1;

        return 0;
    }
}