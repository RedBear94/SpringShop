package com.spring.market.services;

import com.spring.market.entities.Product;
import com.spring.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Все сервисы используется для доп. обработки
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // Вернет контейнер
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        // select p from Product p where price >= 1? and price <= ?2 - собирается полная фильткрация если все параметры не null
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}
