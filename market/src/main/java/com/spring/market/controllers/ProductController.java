package com.spring.market.controllers;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String firstRequest(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1,5));
        return "products";
    }

    @GetMapping("/filter")
    public String getFilteredProducts(Model model, @RequestParam(defaultValue = "0") Integer min, @RequestParam(required = false) Integer max){
        if(max != null) {
            model.addAttribute("products", productService.findAllByPriceGreaterThanAndPriceLessThan(min, max));
        }
        else {
            model.addAttribute("products", productService.findAllByPriceGreaterThan(min));
        }

        return "products";
    }

    // var 1 - Разорвать циклическую ссылку с заказом, добавив @JsonIgnore в классе Product
    /*@GetMapping("/{id}")
    @ResponseBody
    public Product findGetOneProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists")
        );
    }*/

    // var 2 - Использование проекций через интерфейс: Spring Data - может создавать проекции
    @GetMapping("/{id}")
    @ResponseBody
    public ProductDto showOneProduct(Model model, @PathVariable Long id){
        return productService.findDtoById(id).get();
    }
}
