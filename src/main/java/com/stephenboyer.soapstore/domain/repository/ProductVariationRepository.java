package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.ProductVariation;

public interface ProductVariationRepository {
    ProductVariation create(ProductVariation productVariation);
    ProductVariation read(String productVariationId);
    void update(String productVariationId, ProductVariation productVariation);
    void delete(String productVariationId);
}
