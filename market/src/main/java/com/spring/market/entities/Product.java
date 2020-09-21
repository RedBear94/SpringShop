package com.spring.market.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Вариант с разрывом циклической ссылки
    // @JsonIgnore
    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Order> orders;

    @Column(name = "price")
    private int price;
}
