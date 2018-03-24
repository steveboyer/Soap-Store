package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.ProductVariation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductVariationRepositoryImpl {
    private Map<String, ProductVariation> productVariationes;

    public ProductVariationRepositoryImpl(){
        productVariationes = new HashMap<>();
    }

    public ProductVariation create(ProductVariation productVariation){
        if(productVariationes.keySet().contains(productVariation.getId())) {
            throw new IllegalArgumentException(String.format("ProductVariation %s already exists", productVariation.getId()));
        }
        productVariationes.put(productVariation.getId(), productVariation);
        return productVariation;
    }

    public ProductVariation read(String cartId){
        return productVariationes.get(cartId);
    }

    public void update(String productVariationId, ProductVariation productVariation){
        if(!productVariationes.keySet().contains(productVariationId)){
            throw new IllegalArgumentException(String.format("Cannot update because productVariation %s does not exist", productVariation.getId()));
        }

        else productVariationes.put(productVariationId, productVariation);
    }

    public void delete(String productVariationId){
        if(!productVariationes.keySet().contains(productVariationId)){
            throw new IllegalArgumentException(String.format("Cannot update because productVariation %s does not exist", productVariationId));
        }
        productVariationes.remove(productVariationId);
    }
}
