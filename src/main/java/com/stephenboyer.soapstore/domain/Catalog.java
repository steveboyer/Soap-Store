package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.V1InventoryEntry;
import com.stephenboyer.soapstore.util.ProductComparator;
import com.stephenboyer.soapstore.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Catalog {
    private List<Product> products;
    private List<Category> categories;
    private List<V1InventoryEntry> inventory;
    private HashMap<String, ArrayList<Product>> mappedProducts;


    private static final Logger logger = LoggerFactory.getLogger(Catalog.class.getSimpleName());

    public Catalog(List<Product> products, List<Category> categories, List<V1InventoryEntry> inventory){
        this.products = products;
        this.categories = categories;
        this.inventory = inventory;
        mapProducts();
    }

    // Map products to category
    private void mapProducts(){
        mappedProducts = new HashMap<>();

        // Iterate through and set category for each product
        for (Category category : categories) {

            ArrayList<Product> productsInCategory = new ArrayList<>();
            for(Product product : products) {
                if (product.getCategoryId() != null && category != null) {
                    if (product.getCategoryId().equals(category.getCategoryId())) {
                        product.setCategory(category);
                        productsInCategory.add(product);
                        for(ProductVariation productVariation : product.getProductVariations()){
                            logger.info(Strings.toString(product));
                        }

                    }
                }
            }

            products.sort(new ProductComparator());




            mappedProducts.put(category.getId(), productsInCategory);
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
        logger.info("categoryId: " + categoryId);

        return mappedProducts.get(categoryId);
    }

    public Product getProductById(String id){
        for(Product product : products){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public ProductVariation getProductVariationBySku(String productId, String sku){
        List<ProductVariation> products = getProductById(productId).getProductVariations();

        for(ProductVariation variation : products){
            if(variation.getSku().equals(sku)){
                return variation;
            }
        }

        return null;
    }



//    class
    // Use comparator? No, use HashMap<>(). This will use more ram, but will be faster. HashMap should probably be used.

}
