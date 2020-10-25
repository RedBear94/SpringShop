package com.spring.market.controllers;

import com.spring.market.utils.Cart;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Controller
//@RequestMapping("/cart")
//@AllArgsConstructor
public class CartController {
    private Cart cart;

    @GetMapping
    public String showCartPage(HttpSession session) {
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(
            @PathVariable(name = "product_id") Long productId,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        cart.addOrIncrement(productId);
        response.sendRedirect(request.getHeader("referer")); // возвращаемся обратно на перенаправляющую страницу
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }
}