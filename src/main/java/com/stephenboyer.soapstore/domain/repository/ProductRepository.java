package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Product;

public interface ProductRepository {
    Product create(Product product);
    Product read(String productId);
    void update(String productId, Product product);
    void delete(String productId);
}
