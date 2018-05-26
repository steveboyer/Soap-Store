package com.stephenboyer.soapstore.util;

import com.stephenboyer.soapstore.domain.Catalog;
import com.stephenboyer.soapstore.domain.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    /**
     * Sort by whether product has an image and puts
     * products with images first
     * @param o1 product 1
     * @param o2 product 2
     * @return 1/-1 if one product has image and other doesn't or 0
     */
    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getImageComingSoon() && !o2.getImageComingSoon()) return 1;
        if(!o1.getImageComingSoon() && o2.getImageComingSoon()) return -1;

        return 0;
    }
}