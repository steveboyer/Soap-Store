package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Category;

public interface CategoryRepository {
    Category create(Category category);
    Category update(String categoryId);
    void update(String categoryId, Category category);
    void delete(String categoryId);
}
