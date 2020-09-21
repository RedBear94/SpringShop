package com.spring.market.dto;

// Использование проекций DTO - data transfer object
public interface ProductDto {
    // Указываются только нужные поля
    Long getId();
    String getTitle();
    int getPrice();
}
