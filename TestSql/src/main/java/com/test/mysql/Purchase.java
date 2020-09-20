package com.test.mysql;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "money_spent")
    private int money_spent;

    public Purchase(){
    }

    public Purchase(Customer customer, Product product, int money_spent){
        this.customer = customer;
        this.product = product;
        this.money_spent = money_spent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getMoney_spent() {
        return money_spent;
    }

    public void setMoney_spent(int money_spent) {
        this.money_spent = money_spent;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer=" + customer.getName() +
                ", product=" + product.getTitle() +
                ", money_spent=" + money_spent +
                '}';
    }
}
