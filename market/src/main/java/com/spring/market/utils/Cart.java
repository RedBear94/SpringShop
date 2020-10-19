package com.spring.market.utils;

import com.spring.market.entities.OrderItem;
import com.spring.market.entities.Product;
import com.spring.market.exeptions.ResourceNotFoundException;
import com.spring.market.services.ProductService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// @Scope - В качестве корзины испоьзуется сессионный бин т.е.
// Везде где вводится Cart создается и возвращается её копия для каждого клиента
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
// @RequiredArgsConstructor - включена в @Data   // Автоматический создает и заполняет конструктор только final значениями
@Data // добавит Getter/Setter
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int price;
    private int count;

    // Инициализация после создания бина
    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + productId + " (add to cart)"));
        items.add(new OrderItem(p));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        count = 0;
        for (OrderItem o : items) {
            o.setPricePerProduct(o.getProduct().getPrice());
            o.setPrice(o.getProduct().getPrice() * o.getQuantity());
            price += o.getPrice();
            count += o.getQuantity();
        }
    }

    public void clear() {
        price = 0;
        count = 0;
        items.clear();
    }
}
