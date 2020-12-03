package controllers;

import dtos.ProductDto;
import entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/products")
    public ArrayList<ProductDto> getProducts(HttpEntity<List<Long>> longs){
        List<Long> ids = longs.getBody();
        ArrayList<ProductDto> pdto = new ArrayList<>();
        List<Product> products = productService.findAllByListId(ids);
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            pdto.add(productDto);
        }
        return pdto;
    }

}