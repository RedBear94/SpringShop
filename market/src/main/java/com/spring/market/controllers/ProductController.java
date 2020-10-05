package com.spring.market.controllers;

import com.spring.market.dto.ProductDto;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.ProductService;
import com.spring.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id:" + id + " doesn't exist (for edit)"));
        model.addAttribute("product", p);
        return "product edit";
    }

    // @ModelAttribute - из группы request параметров собирается модель
    @PostMapping("/edit")
    public String saveOrUpdate(@ModelAttribute Product product){
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "ok";
    }

    @PostMapping("/showme")
    public void showMeObject(@RequestBody Product p) {
        System.out.println(p);
    }
}
