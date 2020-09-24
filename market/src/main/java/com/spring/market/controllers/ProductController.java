package com.spring.market.controllers;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.services.ProductService;
import com.spring.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String firstRequest(
            Model model,
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        // &min_price=10&title=hello
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
    public String showOneProduct(Model model, @PathVariable Long id,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false, defaultValue = "-1") Integer price){
        if(title != null || price > 0)
            productService.updateById(id, title, price);

        ProductDto product = productService.findDtoById(id).get();
        model.addAttribute("product", product);
        return "product";
    }
}
