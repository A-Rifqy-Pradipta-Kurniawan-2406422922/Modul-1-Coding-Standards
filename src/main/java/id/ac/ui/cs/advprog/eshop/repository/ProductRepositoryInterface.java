package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;
import java.util.UUID;

public interface ProductRepositoryInterface {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findById(UUID productId);
    void update(UUID productId, Product updatedProduct);
    void delete(UUID productId);
}
