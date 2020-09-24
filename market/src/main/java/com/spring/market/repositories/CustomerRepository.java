package com.spring.market.repositories;

import com.spring.market.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Наследует JpaRepository - что позволяет сгенеровать стандартный код с методами:
// findAll(); delete(); save; и другие crud - операции
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // автоматическая генерация кода методов

    // findBy - поиск 1-го findAll - несколько | + имя столбца
    Customer findByName(String name);

    // > minId
    List<Customer> findAllByIdGreaterThan(Long minId);

    // Desc - сортировать по убыванию
    List<Customer> findAllByIdGreaterThanOrderByIdDesc(Long minId);

    // @Query - возвращение через hql запрос | ?1 - принимает переменную
    @Query("select c from Customer c where c.name = ?1")
    Customer findByNameHQL(String customerName);
}
