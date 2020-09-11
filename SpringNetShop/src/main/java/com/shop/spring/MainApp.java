package com.shop.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        /*
        - Есть класс Product (id, название, цена).

        - Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.

        - ProductRepository позволяет получить весь список или один товар по id.

        - Создаем бин Cart, в который можно добавлять и удалять товары по id.

        - Написать консольное приложение, позволяющее управлять корзиной. При каждом запросе корзины из контекста,
        должна создаваться новая корзина.
        */

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        Card card = context.getBean("card", Card.class);
        Card card2 = context.getBean("card", Card.class);

        card.save(productRepository.findProductById(2L));
        card.save(productRepository.findProductById(5L));
        System.out.println(card);

        card.delete(productRepository.findProductById(2L));
        System.out.println(card);

        System.out.println(card2); // empty

        context.close();
    }
}
