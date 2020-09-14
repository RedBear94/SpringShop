package com.shop.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
        try {
            this.products.add(productRepository.findProductById(id));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(Long id) {
        try {
            this.products.remove(getProductById(id));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(Long id) throws ResourceNotFoundException {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ResourceNotFoundException("Product with id=" + id + " not found");
    }

    @Override
    public String toString() {
        return "Card - products: " + products;
    }
}
