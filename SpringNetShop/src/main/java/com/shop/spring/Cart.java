package com.shop.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> products;
    private ProductRepository productRepository;

    @Autowired
    public Cart(List<Product> products, ProductRepository productRepository){
        this.products = products;
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }

    public void addProductById(Long id) {
        this.products.add(productRepository.findProductById(id));
    }

    public void deleteProductById(Long id) {
        this.products.remove(getProductById(id));
    }

    public Product getProductById(Long id){
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("Item not found");
    }

    @Override
    public String toString() {
        return "Card - products: " + products;
    }
}
