package com.spring.market.services;

import com.spring.market.entities.Customer;
import com.spring.market.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findByNameAndAddIfDoesnExist(String name) {
        if (customerRepository.findByName(name) == null) {
            Customer customer = new Customer();
            customer.setName(name);
            customerRepository.save(customer);
        }
        return customerRepository.findByName(name);
    }
}
