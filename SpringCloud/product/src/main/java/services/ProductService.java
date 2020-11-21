package services;

import entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllByListId(List<Long> ids){
        return productRepository.findAllByListId(ids);
    }
}
