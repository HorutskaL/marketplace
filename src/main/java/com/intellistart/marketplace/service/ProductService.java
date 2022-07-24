package com.intellistart.marketplace.service;

import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }
    public void update(int id, Product updateProduct){
        Product productToBeUpdated = productRepository.findById(id).get();
        updateProduct.setId(id);
        updateProduct.setUsers(productToBeUpdated.getUsers());
        productRepository.save(updateProduct);
    }

    public void buy(int id, User buyer, Product product) {
        if (buyer.getAmountOfMoney() > product.getPrice()) {
            buyer.setAmountOfMoney(buyer.getAmountOfMoney()- product.getPrice());
        }
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

}
