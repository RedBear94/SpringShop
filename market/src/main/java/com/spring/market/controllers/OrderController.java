package com.spring.market.controllers;

import com.spring.market.dto.OrderDto;
import com.spring.market.entities.Order;
import com.spring.market.entities.User;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.OrderService;
import com.spring.market.services.UserService;
import com.spring.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// Crud операции построенные по правилам REST API
@RestController // Все методы получают ResponseBody (возвращают json)
@RequestMapping("/api/v1/orders") // v1 - версирование
@RequiredArgsConstructor // Конструктор для final переменных
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllUsersOrdersDtoByUserName(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address);
        orderService.save(order);
        cart.clear();
    }
}
