package com.spring.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Data // lombok - подключен в pom: для полей класса формирует getter и setter
@NoArgsConstructor // Пустой конструктор будет добавлен в класс
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    // в orders есть ссылка через переменную customer типа Customer
    @OneToMany(mappedBy = "customer")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Order> orders;
}
