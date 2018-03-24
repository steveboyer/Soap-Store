package com.stephenboyer.soapstore.domain;

import com.stephenboyer.soapstore.util.Strings;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Entity
//@Table(name = "cart")

public class Cart implements Serializable {
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    public String id;


//    List<CartItem> items;

//    @Column(name = "quantity")
    int quantity;

//    @Column(name = "total")
    long total;

//    @Column(name = "sales_tax")
    long sales_tax;

//    @Column(name = "subtotal")
    long subtotal;

    public Cart(){
//        items = new ArrayList<>();

        quantity = 0;
        sales_tax = 0;
        subtotal = 0;
        total = 0;
    }

    public Cart(int quantity, long total, long sales_tax, long subtotal){
//        this.items = items;
        this.quantity = quantity;
        this.total = total;
        this.sales_tax = sales_tax;
        this.subtotal = subtotal;
    }

    public Cart(String id){
        this.id = id;
    }

//    public Cart(CartItem item){
//        items = new ArrayList<>();
//        items.add(item);
//    }

    public String getId() {
        return id;
    }

    public void setId(String _d) {
        this.id = id;
    }

    public void addItem(CartItem item){
//        if(items == null) {
//            items = new ArrayList<>();
//        }
//        items.add(item);
//        quantity = items.size();
    }

//    public List<CartItem> getItems() {
//        return items;
//    }

//    public void setItems(List<CartItem> items) {
//        this.items = items;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSales_tax() {
        return sales_tax;
    }

    public void setSales_tax(long sales_tax) {
        this.sales_tax = sales_tax;
    }

    public long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
