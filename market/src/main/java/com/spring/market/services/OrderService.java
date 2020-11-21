package com.spring.market.services;

import com.spring.market.dto.OrderDto;
import com.spring.market.dto.OrderItemDto;
import com.spring.market.entities.Order;
import com.spring.market.repositories.OrderRepository;
import com.spring.market.soap.ItemOrder;
import com.spring.market.soap.OrderXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<OrderXML> findAllUserOrderXMLByUsername(String username) {
        List<OrderDto> orderDto = this.findAllUsersOrdersDtoByUserName(username);
        List<OrderXML> ordersXML = new ArrayList<>();
        for (OrderDto dto : orderDto) {
            OrderXML orderXML = new OrderXML();
            orderXML.setAddress(dto.getAddress());
            orderXML.setPrice(dto.getPrice());
            List<OrderItemDto> orderItemDtos = dto.getItems();
            for (OrderItemDto orderItemDto : orderItemDtos) {
                ItemOrder itemOrder = new ItemOrder();
                itemOrder.setProductId(orderItemDto.getProductId());
                itemOrder.setProductTitle(orderItemDto.getProductTitle());
                itemOrder.setPrice(orderItemDto.getPrice());
                itemOrder.setPricePerProduct(orderItemDto.getPricePerProduct());
                itemOrder.setQuantity(orderItemDto.getQuantity());
                orderXML.getItemOrder().add(itemOrder);
            }
            ordersXML.add(orderXML);
        }
        return ordersXML;
    }
}