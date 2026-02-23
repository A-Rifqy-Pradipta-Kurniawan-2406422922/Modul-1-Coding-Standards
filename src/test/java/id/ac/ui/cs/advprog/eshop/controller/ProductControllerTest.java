package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void createProductPageSetsProductAndReturnsTemplate() {
        Model model = new ConcurrentModel();

        String view = productController.createProductPage(model);

        assertEquals("CreateProduct", view);
        assertNotNull(model.getAttribute("product"));
    }

    @Test
    void createProductPostCreatesProductAndRedirects() {
        Product product = new Product();

        String view = productController.createProductPost(product, new ConcurrentModel());

        verify(productService).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void productListPageAddsProductsAndReturnsTemplate() {
        Model model = new ConcurrentModel();
        Product p1 = new Product();
        Product p2 = new Product();
        List<Product> products = List.of(p1, p2);
        when(productService.findAll()).thenReturn(products);

        String view = productController.productListPage(model);

        verify(productService).findAll();
        assertEquals(products, model.getAttribute("products"));
        assertEquals("ProductList", view);
    }

    @Test
    void editProductPageAddsProductAndReturnsTemplate() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(product);
        Model model = new ConcurrentModel();

        String view = productController.editProductPage(productId, model);

        verify(productService).findById(productId);
        assertEquals(product, model.getAttribute("product"));
        assertEquals("EditProduct", view);
    }

    @Test
    void editProductPostUpdatesProductAndRedirects() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setProductId(productId);

        String view = productController.editProductPost(product, new ConcurrentModel());

        verify(productService).update(productId, product);
        assertEquals("redirect:list", view);
    }

    @Test
    void deleteProductDeletesAndRedirects() {
        UUID productId = UUID.randomUUID();

        String view = productController.deleteProduct(productId);

        verify(productService).delete(productId);
        assertEquals("redirect:/product/list", view);
    }
}
