package com.spring.market.repositories;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // Метод вернет версию объекта, а не сам объект, самостоятельно запросив из базы
    Optional<ProductDto> findOneById(Long id);
}
