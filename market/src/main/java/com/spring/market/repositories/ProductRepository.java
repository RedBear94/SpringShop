package com.spring.market.repositories;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Метод вернет версию объекта, а не сам оюъект, саостоятельно запросив из базы
    Optional<ProductDto> findOneById(Long id);

    List<Product> findAllByPriceGreaterThan(int min);

    List<Product> findAllByPriceGreaterThanAndPriceLessThan(int min, int max);
}
