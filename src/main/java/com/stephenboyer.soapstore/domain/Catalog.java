package com.stephenboyer.soapstore.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalog {
    private List<Product> products;
    private List<Category> categories;
    private HashMap<Category, ArrayList<Product>> mappedProducts;
    private static final transient Logger log = LoggerFactory.getLogger(Catalog.class.getSimpleName());

    public Catalog(List<Product> products, List<Category> categories){
        this.products = products;
        this.categories = categories;
        mapProducts();
    }


    // Map products to category
    private void mapProducts(){
        mappedProducts = new HashMap<>();

        // Iterate through and set category for each product
        for (Category category : categories) {
                ArrayList<Product> productsInCategory = new ArrayList<>();
                 for(Product product : products) {
                    if (product.getCategoryId() == null) continue;
                    if (category == null) continue;

                    if (product.getCategoryId().replace(" ", "-").toLowerCase().equals(category.getId())) {
                        product.setCategory(category);
                        productsInCategory.add(product);
                        break;
                    }

            }
            mappedProducts.put(category, productsInCategory);
        }
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

    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategory(String id) {
        for (Category category : getCategories()) {
            if (id.equals(category.getName().toLowerCase().replace(" ", "-"))) {
                return category;
            }
        }

        return null;
    }

    /**
     * Get products matching @param category
     * @param categoryId
     * @return
     */
    public List<Product> getProductsInCategory(String categoryId){
        log.info("categoryId: " + categoryId);

        List<Product> products = new ArrayList<>();

        List<Product> allProducts = getProducts();


        for(Product product : allProducts){

            if(product.getCategory() != null && product.getCategory().getId().equals(categoryId)){
                products.add(product);
            }
        }

        return products;
    }

    public Product getProduct(String id){
        for(Product product : products){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public HashMap<Category, ArrayList<Product>> getMappedProducts(){
        return mappedProducts;
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
