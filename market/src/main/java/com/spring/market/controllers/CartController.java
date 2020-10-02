package com.spring.market.controllers;

import com.spring.market.entities.Order;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.OrderService;
import com.spring.market.utils.Cart;
import com.spring.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private OrderService orderService;
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
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " doesn't exists (add to cart"));
        cart.addOrIncrement(p);
        response.sendRedirect(request.getHeader("referer")); // возвращаемся обратно на перенаправляющую страницу
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.incrementOnly(productId);
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

    @GetMapping("/order")
    public String placeYourOrder(HttpSession httpSession){
        return "order";
    }

    @PostMapping("/order")
    @ResponseBody
    public String saveOrder(@ModelAttribute Order order){
        //orderService.saveOrUpdate(order);
        //return "redirect:/products";
        return "ok";
    }
}