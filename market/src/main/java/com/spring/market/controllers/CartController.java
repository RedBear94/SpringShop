package com.spring.market.controllers;

import com.spring.market.dto.CartDto;
import com.spring.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // над всеми методами добавит @ResponseBody
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
    }

    @GetMapping("/dec/{product_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
    }

    @GetMapping("/remove/{product_id}")
    public void removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
    }

    @GetMapping
    public CartDto getCartDto() {
        cart.recalculate();
        // преобразование OrderItem в OrderItemDto
        return new CartDto(cart);
    }
}