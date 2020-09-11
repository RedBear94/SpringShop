package com.shop.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Card {
    private List<Product> products;

    public Card(){
        this.products = new ArrayList<>();
    }


    public void save(Product product) {
        products.add(0, product);
    }

    public void delete(Product product) {
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Card - products: " + products;
    }
}
