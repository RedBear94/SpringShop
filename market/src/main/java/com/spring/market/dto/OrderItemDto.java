package com.spring.market.dto;

import com.spring.market.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    //@ManyToOne
    //@JoinColumn(name = "product_id")
    //private Product product;
    private Long productId;

    private String productTitle;

    private int quantity;

    private int pricePerProduct;

    private int price;

    public OrderItemDto(OrderItem o) {
        this.productId = o.getProduct().getId();
        this.productTitle = o.getProduct().getTitle();
        this.quantity = o.getQuantity();
        this.price = o.getPrice();
        this.pricePerProduct = o.getPricePerProduct();
    }
}
