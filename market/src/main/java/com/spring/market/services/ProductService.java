package com.spring.market.services;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Сервисы используется для доп. обработки

@Service
@AllArgsConstructor // Конструктор со всеми полями
public class ProductService {
    private ProductRepository productRepository;

    // Вернет контейнер
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findDtoById(Long id){
        return productRepository.findOneById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        // select p from Product p where price >= 1? and price <= ?2 - собирается полная фильткрация если все параметры не null
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by("id")));
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
