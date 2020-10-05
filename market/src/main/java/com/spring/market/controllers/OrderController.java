package com.spring.market.controllers;

import com.spring.market.entities.Customer;
import com.spring.market.entities.Order;
import com.spring.market.services.CustomerService;
import com.spring.market.services.OrderService;
import com.spring.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private CustomerService customerService;
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
    public String save(@ModelAttribute Order order,
        @RequestParam(name = "name") String customerName
        //@RequestParam(name = "phone") String customerPhone,
        //@RequestParam(name = "address") String customerAddress
    ) {
        order.setCustomer(customerService.findByNameAndAddIfDoesnExist(customerName));
        order.setPrice(cart.getPrice()); // записываем цену из корзины
        cart.getItems().forEach(io -> io.setOrder(order)); // Каждому OrderItem указываем что он ссылается на Order
        order.setItems(cart.getItems());
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}
