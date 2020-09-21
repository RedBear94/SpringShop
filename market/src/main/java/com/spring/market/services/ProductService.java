package com.spring.market.services;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Сервисы используется для доп. обработки

@Service
@AllArgsConstructor // Конструктор со всеми полями
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    // Вернет контейнер
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findDtoById(Long id){
        return productRepository.findOneById(id);
    }

    public List<Product> findAllByPriceGreaterThan(int min){
        return productRepository.findAllByPriceGreaterThan(min);
    }

    public List<Product> findAllByPriceGreaterThanAndPriceLessThan(int min, int max){
        List<Product> filteredProducts = productRepository.findAllByPriceGreaterThanAndPriceLessThan(min, max);
        return productRepository.findAllByPriceGreaterThanAndPriceLessThan(min, max);
    }
}
