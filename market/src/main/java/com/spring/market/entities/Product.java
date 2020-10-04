package com.spring.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data // lombok - подключен в pom: для полей класса формирует getter и setter
@NoArgsConstructor // Пустой конструктор будет добавлен в класс
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;
    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

}
