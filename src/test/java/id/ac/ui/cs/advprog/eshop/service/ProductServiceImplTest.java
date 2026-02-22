package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        productService.update(product.getProductId(), product);

        verify(productRepository, times(1)).update(product.getProductId(), product);
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();

        productService.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();

        Product result = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAllProduct() {
        Product product1 = new Product();
        Product product2 = new Product();
        Iterator<Product> iterator = Arrays.asList(product1, product2).iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    void testFindByIdProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);

        Product result = productService.findById(productId);

        verify(productRepository, times(1)).findById(productId);
        assertEquals(product, result);
    }
}
