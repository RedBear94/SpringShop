package com.test.mysql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            // Start
            session = factory.getCurrentSession();
            session.beginTransaction();

            // Наборы проверок

            // Test 1 (worked)
            Customer user = session.get(Customer.class, 1L);
            user.print();

            // Test 2 (worked)
            Customer user2 = new Customer();
            user2.setName("Bob2");
            session.save(user2);
            user2.print();

            // Test 3 (worked)
            Product firstProduct = session.get(Product.class, 1L);
            firstProduct.print();

            // Test 4 (worked)
            for (int i = 0; i < 10; i++) {
                Product product = new Product();
                product.setTitle("Sphere" + i);
                product.setPrice(i);
                session.save(product);
                product.print();
            }
            // Apply
            session.getTransaction().commit();

            // ---------------------------------

            // Новая сессия
            session = factory.getCurrentSession();
            session.beginTransaction();

            // Test: Какие товары покупал клиент с id = 1 (это Bob) (worked)
            List<Purchase> purchases1 = session.createQuery("SELECT p FROM Purchase p where p.customer.id = :id", Purchase.class)
                    .setParameter("id", 1L).getResultList();
            for(Purchase p : purchases1){
                System.out.println(p);
            }

            // Test: какие клиенты купили товар с id = 2 (это cheese) (worked)
            List<Purchase> purchases2 = session.createQuery("SELECT p FROM Purchase p where p.product.id = :id", Purchase.class)
                    .setParameter("id", 2L).getResultList();
            for(Purchase p : purchases2){
                System.out.println(p);
            }

            // Test: Удаление из базы товаров/покупателей (worked)
            session.delete(session.get(Customer.class, 1L));
            session.delete(session.get(Product.class, 1L));

            // Apply
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
