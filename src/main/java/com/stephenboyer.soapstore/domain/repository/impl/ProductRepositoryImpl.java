package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepositoryImpl {
    private Map<String, Product> products;

    public ProductRepositoryImpl(){
        products = new HashMap<>();
    }

    public Product create(Product product){
        if(products.keySet().contains(product.getId())) {
            throw new IllegalArgumentException(String.format("Product %s already exists", product.getId()));
        }
        products.put(product.getId(), product);
        return product;
    }

    public Product read(String cartId){
        return products.get(cartId);
    }

    public void update(String productId, Product product){
        if(!products.keySet().contains(productId)){
            throw new IllegalArgumentException(String.format("Cannot update because product %s does not exist", product.getId()));
        }

        else products.put(productId, product);
    }

    public void delete(String productId){
        if(!products.keySet().contains(productId)){
            throw new IllegalArgumentException(String.format("Cannot update because product %s does not exist", productId));
        }
        products.remove(productId);
    }
}
