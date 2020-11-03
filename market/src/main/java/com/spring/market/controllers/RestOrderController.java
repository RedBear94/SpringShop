package com.spring.market.controllers;

import com.spring.market.entities.Order;
import com.spring.market.entities.User;
import com.spring.market.services.OrderService;
import com.spring.market.services.UserService;
import com.spring.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// Crud операции построенные по правилам REST API
@RestController // Все методы получают ResponseBody (возвращают json)
@RequestMapping("/api/v1/order") // v1 - версирование
@RequiredArgsConstructor
public class RestOrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping(produces = "application/json")
    public List<Order> showOrders() {
        return orderService.findAll();
    }

    @PostMapping("/confirm")
    @ResponseBody
    public void confirmOrder(Principal principal,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "receiver_name") String receiverName,
                               @RequestParam(name = "phone_number") String phone
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address);
        order = orderService.save(order);
    }
}
