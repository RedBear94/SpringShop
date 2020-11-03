package com.spring.market.services;

import com.spring.market.dto.OrderDto;
import com.spring.market.entities.Order;
import com.spring.market.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderDto> findAllUsersOrdersDtoByUserName(String username) {
        return orderRepository.findAllOrdersByUserName(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}