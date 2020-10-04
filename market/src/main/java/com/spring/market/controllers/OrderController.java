package com.spring.market.controllers;

import com.spring.market.entities.Customer;
import com.spring.market.entities.Order;
import com.spring.market.services.OrderService;
import com.spring.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/order")
    public String doOrder(Model model) {
        return "order";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Order order) {
        order.setCost(cart.getPrice()); // записываем цену из корзины
        cart.getItems().forEach(io -> io.setOrder(order)); // Каждому OrderItem указываем что он ссылается на Order
        order.setItems(cart.getItems());
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}
