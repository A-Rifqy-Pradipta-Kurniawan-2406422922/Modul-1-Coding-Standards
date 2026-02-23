package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productIterator.next();
        assertTrue(productIterator.hasNext());
        productIterator.next();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Baru");
        updatedProduct.setProductQuantity(150);

        productRepository.update(product.getProductId(), updatedProduct);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Sampo Cap Baru", foundProduct.getProductName());
        assertEquals(150, foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testFindByIdIfNotFound() {
        Product foundProduct = productRepository.findById(UUID.randomUUID());
        assertNull(foundProduct);
    }

    @Test
    void testFindByIdSkipsNonMatchingProductBeforeMatch() {
        Product firstProduct = new Product();
        firstProduct.setProductName("First");
        firstProduct.setProductQuantity(1);
        productRepository.create(firstProduct);

        Product secondProduct = new Product();
        secondProduct.setProductName("Second");
        secondProduct.setProductQuantity(2);
        productRepository.create(secondProduct);

        Product foundProduct = productRepository.findById(secondProduct.getProductId());

        assertNotNull(foundProduct);
        assertEquals(secondProduct.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testUpdateOnNonExistentProduct() {
        Product product1 = new Product();
        product1.setProductName("Original Product");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product updatedInfo = new Product();
        updatedInfo.setProductName("Updated Product");
        updatedInfo.setProductQuantity(50);

        productRepository.update(UUID.randomUUID(), updatedInfo);

        Product foundProduct = productRepository.findById(product1.getProductId());
        assertEquals("Original Product", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteOnNonExistentProduct() {
        Product product1 = new Product();
        product1.setProductName("Original Product");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        productRepository.delete(UUID.randomUUID());

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productIterator.next();
        assertFalse(productIterator.hasNext());
    }
}
