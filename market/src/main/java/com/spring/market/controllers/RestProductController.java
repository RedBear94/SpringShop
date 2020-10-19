package com.spring.market.controllers;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.repositories.ProductRepository;
import com.spring.market.services.ProductService;
import com.spring.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// Crud операции построенные по правилам REST API
@RestController // Все методы получают ResponseBody (возвращают json)
@RequestMapping("/api/v1/products") // v1 - версирование
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping(produces = "application/json") // /api/v1/products - Запрос данных
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

    @GetMapping(value = "/{id}", produces = "application/json")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findDtoById(id).get();
        //return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id:" + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json") // Добавление данных
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json") // Модификация данных
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
