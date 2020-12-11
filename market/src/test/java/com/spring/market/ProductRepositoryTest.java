package com.spring.market;

import com.spring.market.entities.Product;
import com.spring.market.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void categoryRepositoryTest() {
        Product product = new Product();
        product.setTitle("Fruit");
        product.setPrice(100);
        testEntityManager.persist(product);
        testEntityManager.flush();

        List<Product> productEntities = productRepository.findAll();

        Assertions.assertEquals(39, productEntities.size());
        Assertions.assertEquals("Fruit", productEntities.get(38).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productEntities = productRepository.findAll();
        Assertions.assertEquals(38, productEntities.size());
    }
}