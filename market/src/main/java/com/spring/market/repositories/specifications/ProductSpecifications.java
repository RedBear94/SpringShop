package com.spring.market.repositories.specifications;

import com.spring.market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        // root - в данном случае это продукт у которого запрашивается цена через get
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice); // where p.price >= minPrice
    }

    public static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice); // where p.price <= maxPrice
    }

    // Поиск по названию продукта
    public static Specification<Product> titleLike(String titlePart) {
        // запрашивается заголовок и он должен быть как %%%s%% | %titlePart% - указывает что titlePart может находится в любом месте названия
        // titlePart% заголовок начинается с указанного слова | %titlePart - закнчиваетс укзанным словом | %% - в s - экранирование что-бы не ожидалось значение
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where p.title like %titlePart%
    }
}
