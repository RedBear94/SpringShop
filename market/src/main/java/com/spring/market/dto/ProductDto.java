package com.spring.market.dto;

import com.spring.market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

// Использование проекций DTO - data transfer object
@Data
@NoArgsConstructor
public class ProductDto {
    // Указываются только нужные поля
    private Long id;
    private String title;
    private int price;
    private String categoryTitle;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getCategory().getTitle();
    }
}
