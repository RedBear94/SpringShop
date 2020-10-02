package com.spring.market.services;

import com.spring.market.entities.Order;
import com.spring.market.entities.Product;
import com.spring.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }
}
