package com.spring.market.controllers;

import com.spring.market.dto.CartDto;
import com.spring.market.dto.OrderItemDto;
import com.spring.market.entities.OrderItem;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.OrderService;
import com.spring.market.services.ProductService;
import com.spring.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController // над всеми методами @ResponseBody
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class RestCartController {
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
    public CartDto getCartDto(){
        cart.recalculate();
        // преобразование OrderItem в OrderItemDto
        return new CartDto(cart);
    }
}