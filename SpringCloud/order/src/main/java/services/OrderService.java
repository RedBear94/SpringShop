package services;

import entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
