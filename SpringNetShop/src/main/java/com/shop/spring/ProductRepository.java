package com.shop.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Scope("singleton") // singleton | prototype | session
public class ProductRepository {
    private List<Product> products;

    // Добавление товаров при старте
    @PostConstruct
    public void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1L,"Bread",10),
                new Product(2L,"Milk",20),
                new Product(3L,"Tomato",30),
                new Product(4L,"Potato",40),
                new Product(5L,"Cheese",50)
        ));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Product findProductById(Long id) throws ResourceNotFoundException {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ResourceNotFoundException("Product with id=" + id + " not found");
    }
}
