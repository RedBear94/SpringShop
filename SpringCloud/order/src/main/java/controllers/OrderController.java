package controllers;

import dtos.OrderDto;
import dtos.ProductDto;
import entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import services.OrderService;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("/order")
    public ResponseEntity newOrder(@RequestBody List<Long> ids) {
        HttpEntity<List<Long>> longs = new HttpEntity<>(new ArrayList<>(ids));
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange("http://product/getproducts", HttpMethod.POST, longs, new ParameterizedTypeReference<List<ProductDto>>() {
        });
        List<ProductDto> dtos = response.getBody();
        if (dtos.size() != ids.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any id`s not found");
        }
        int orderPrice = dtos.stream().mapToInt(ProductDto::getPrice).reduce(Integer::sum).getAsInt();
        Order order = new Order();
        order.setItems(ids);
        order.setPrice(orderPrice);
        long id = orderService.save(order).getId();

        return ResponseEntity.status(HttpStatus.OK).body("order created. id = " + id);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderService.findAll();
        List<Long> ids = getUniqueIds(orders);

        HttpEntity<List<Long>> longs = new HttpEntity<>(new ArrayList<>(ids));
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange("http://product/getproducts", HttpMethod.POST, longs, new ParameterizedTypeReference<List<ProductDto>>() {
        });
        List<ProductDto> products = response.getBody();

        return mapToOrderDto(orders, products);
    }

    private static List<OrderDto> mapToOrderDto(List<Order> orders, List<ProductDto> products) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto o = new OrderDto();
            o.setId(order.getId());
            o.setPrice(order.getPrice());
            for (Long item : order.getItems()) {
                for (ProductDto product : products) {
                    if (item.equals(product.getId())) {
                        o.getOrderItems().add(product);
                    }
                }
            }
            orderDtos.add(o);
        }
        return orderDtos;
    }

    private static List<Long> getUniqueIds(List<Order> orders) {
        Set<Long> unique = new HashSet<>();
        for (Order order : orders) {
            unique.addAll(order.getItems());
        }
        List<Long> ids = new ArrayList<>(unique);
        return ids;
    }
}