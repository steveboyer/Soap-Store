package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.soap.SquareConnector;

import java.util.List;

public class Catalog {
    private List<Product> products;
    private List<Category> categories;

    public Catalog(){
        SquareConnector sq = new SquareConnector();
        products = sq.getProducts(0);
        categories = sq.getCategories();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

//    public ProductVariation findProductVariationBySku(String sku){
//        for(Product product : products){
//            for(ProductVariation productVariation : product.getProductVariations()){
//                if(productVariation.getSku().equalsIgnoreCase(sku)){
//                    return productVariation;
//                }
//            }
//        }
//    }

//    class
        // Use comparator? No, use HashMap<>(). This will use more ram, but will be faster. HashMap should probably be used.

}
