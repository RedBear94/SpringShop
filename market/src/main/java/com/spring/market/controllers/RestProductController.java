package com.spring.market.controllers;

import com.spring.market.entities.Product;
import com.spring.market.repositories.ProductRepository;
import com.spring.market.services.ProductService;
import com.spring.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Crud операции построенные по правилам REST API
@RestController // Все методы получают ResponseBody (возвращают json)
@RequestMapping("/api/v1/products") // v1 - версирование
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;
    private ProductRepository productRepository;

    @GetMapping // /api/v1/products - Запрос данных
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam Map<String, String> params
    ) {
        if(page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 10);
        return content;
    }

    @GetMapping("/size")
    public int getCount(){
        return productRepository.findAll().size();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping // Добавление данных
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping // Модификация данных
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping  // Удаление данных
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
