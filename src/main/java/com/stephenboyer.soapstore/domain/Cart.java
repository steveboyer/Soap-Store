package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
import java.io.Serializable;
import java.util.*;

@Component
@Scope(value="session")
public class Cart {
    private static final Logger logger = LoggerFactory.getLogger(Cart.class.getSimpleName());

    private List<LineItem> lineItems = new ArrayList<>();

    private Long total;

    private Long sales_tax;

    private Long subtotal;


    private Calendar created;

    public Cart(){
        sales_tax = 0L;
        subtotal = 0L;
        total = 0L;
        created = Calendar.getInstance();
    }

    public void addItem(String sku, Integer quantity){
        if(!lineItems.contains(new LineItem(sku, quantity))){
            LineItem item = new LineItem("", sku, "", quantity);
            lineItems.add(item);
        } else {
            for(LineItem item : lineItems){
                if(item.getProductSku().equals(sku)){
                    logger.info(item.toString());
                    logger.info("quantity: " + quantity);

                    Integer q = item.getQuantity();
                    q += quantity;
                    item.setQuantity(q);

                    logger.info(item.toString());

                }
            }
        }
    }


    public void setCreated(Calendar created) {
        this.created = created;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSales_tax() {
        return sales_tax;
    }

    public void setSales_tax(Long sales_tax) {
        this.sales_tax = sales_tax;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public Calendar getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
