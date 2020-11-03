package com.spring.market.repositories;

import com.spring.market.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Выбрать из заказа те где имя пользователя равно 1-ому параметру ?1
    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findAllOrdersByUserName(String username);
}
