package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepositoryImpl {
    private Map<String, Category> categories;

    public CategoryRepositoryImpl(){
        categories = new HashMap<>();
    }

    public Category create(Category category){
        if(categories.keySet().contains(category.getId())) {
            throw new IllegalArgumentException(String.format("Category %s already exists", category.getId()));
        }
        categories.put(category.getId(), category);
        return category;
    }

    public Category read(String cartId){
        return categories.get(cartId);
    }

    public void update(String categoryId, Category category){
        if(!categories.keySet().contains(categoryId)){
            throw new IllegalArgumentException(String.format("Cannot update because category %s does not exist", category.getId()));
        }

        else categories.put(categoryId, category);
    }

    public void delete(String categoryId){
        if(!categories.keySet().contains(categoryId)){
            throw new IllegalArgumentException(String.format("Cannot update because category %s does not exist", categoryId));
        }
        categories.remove(categoryId);
    }
}
