package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;
import java.util.UUID;

public interface ProductRepositoryRead {
    Iterator<Product> findAll();
    Product findById(UUID productId);
}
