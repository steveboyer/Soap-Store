package com.stephenboyer.soapstore.domain.repository.impl;

import com.stephenboyer.soapstore.domain.LineItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LineItemRepositoryImpl {
    private Map<String, LineItem> lineItemes;

    public LineItemRepositoryImpl(){
        lineItemes = new HashMap<>();
    }

    public LineItem create(LineItem lineItem){
        if(lineItemes.keySet().contains(lineItem.getId())) {
            throw new IllegalArgumentException(String.format("LineItem %s already exists", lineItem.getId()));
        }
        lineItemes.put(lineItem.getId(), lineItem);
        return lineItem;
    }

    public LineItem read(String cartId){
        return lineItemes.get(cartId);
    }

    public void update(String lineItemId, LineItem lineItem){
        if(!lineItemes.keySet().contains(lineItemId)){
            throw new IllegalArgumentException(String.format("Cannot update because lineItem %s does not exist", lineItem.getId()));
        }

        else lineItemes.put(lineItemId, lineItem);
    }

    public void delete(String lineItemId){
        if(!lineItemes.keySet().contains(lineItemId)){
            throw new IllegalArgumentException(String.format("Cannot update because lineItem %s does not exist", lineItemId));
        }
        lineItemes.remove(lineItemId);
    }
}
