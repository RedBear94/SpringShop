package com.spring.market.controllers;

import com.spring.market.entities.Order;
import com.spring.market.entities.User;
import com.spring.market.services.OrderService;
import com.spring.market.services.UserService;
import com.spring.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    Cart cart;

    @GetMapping
    public String showOrders(Model model, Principal principal) {
        // model.addAttribute("orders", orderService.findAll());
        model.addAttribute("username", principal.getName());
        List<Order> userOrders = orderService.findUserOrders(userService.findByUsername(principal.getName()));
        model.addAttribute("orders", userOrders);
        return "orders";
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "create_order";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "receiver_name") String receiverName,
                               @RequestParam(name = "phone_number") String phone
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address);
        order = orderService.save(order);
        return "Ваш заказ #" + order.getId();
    }

    // OLD HomeWork code
    /*@GetMapping("/order")
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
    }*/
}
