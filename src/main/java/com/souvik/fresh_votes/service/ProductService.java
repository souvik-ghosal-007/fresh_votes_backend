package com.souvik.fresh_votes.service;

import com.souvik.fresh_votes.domain.Product;
import com.souvik.fresh_votes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(Long id) {
        final Optional<Product> res = productRepository.findById(id);

        Product product = null;
        if(res.isPresent()) {
            product = res.get();
        }

        return product;
    }

    public List<Product> findProductsByUserId(Long id) {
        return productRepository.findProductsByUserId(id);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
