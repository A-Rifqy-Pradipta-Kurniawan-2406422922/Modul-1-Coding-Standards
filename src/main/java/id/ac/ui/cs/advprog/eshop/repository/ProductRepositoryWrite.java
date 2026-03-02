package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.UUID;

public interface ProductRepositoryWrite {
    Product create(Product product);
    void update(UUID productId, Product updatedProduct);
    void delete(UUID productId);
}
