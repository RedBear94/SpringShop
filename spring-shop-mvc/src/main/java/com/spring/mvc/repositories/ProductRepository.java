package com.spring.mvc.repositories;

import com.spring.mvc.model.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Milk", 100));
        products.add(new Product(2L, "Bred", 200));
        products.add(new Product(3L, "Cheese", 300));
        products.add(new Product(4L, "Tea", 400));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void save(Product product) {
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public Long getFreeId() {
        Long id = 1L;
        boolean flag = true;
        while (flag) {
            for (Product p : products) {
                if (id.equals(p.getId())) {
                    flag = true;
                    id = id + 1L;
                    break;
                } else {
                    flag = false;
                }
            }
        }
        return id;
    }
}
