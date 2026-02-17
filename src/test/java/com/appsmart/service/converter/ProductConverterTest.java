package com.appsmart.service.converter;

import com.appsmart.api.response.ProductResponse;
import com.appsmart.persistance.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductConverterTest {

    private ProductConverter productConverter;

    @BeforeEach
    void setUp() {
        productConverter = new ProductConverter();
    }

    @Test
    void to_shouldConvertProductToProductResponse() {
        UUID productId = UUID.randomUUID();
        String title = "Test Product";
        String description = "Test Description";
        BigDecimal price = new BigDecimal("99.99");

        Product product = new Product();
        product.setId(productId);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);

        ProductResponse result = productConverter.to(product);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
        assertEquals(price, result.getPrice());
    }

    @Test
    void to_shouldReturnNullWhenInputIsNull() {
        ProductResponse result = productConverter.to(null);

        assertNull(result);
    }

    @Test
    void from_shouldConvertProductResponseToProduct() {
        UUID productId = UUID.randomUUID();
        String title = "Test Product";
        String description = "Test Description";
        BigDecimal price = new BigDecimal("99.99");

        ProductResponse response = new ProductResponse();
        response.setId(productId);
        response.setTitle(title);
        response.setDescription(description);
        response.setPrice(price);

        Product result = productConverter.from(response);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
        assertEquals(price, result.getPrice());
    }

    @Test
    void from_shouldReturnNullWhenInputIsNull() {
        Product result = productConverter.from(null);

        assertNull(result);
    }

    @Test
    void allTo_shouldConvertAllProductsToResponses() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = new Product();
        product1.setId(productId1);
        product1.setTitle("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(new BigDecimal("10.00"));

        Product product2 = new Product();
        product2.setId(productId2);
        product2.setTitle("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(new BigDecimal("20.00"));

        List<Product> products = Arrays.asList(product1, product2);

        Iterable<ProductResponse> result = productConverter.allTo(products);

        assertNotNull(result);
        List<ProductResponse> resultList = (List<ProductResponse>) result;
        assertEquals(2, resultList.size());
        assertEquals(productId1, resultList.get(0).getId());
        assertEquals("Product 1", resultList.get(0).getTitle());
        assertEquals("Description 1", resultList.get(0).getDescription());
        assertEquals(new BigDecimal("10.00"), resultList.get(0).getPrice());
        assertEquals(productId2, resultList.get(1).getId());
        assertEquals("Product 2", resultList.get(1).getTitle());
    }

    @Test
    void allTo_shouldReturnNullWhenInputIsNull() {
        Iterable<ProductResponse> result = productConverter.allTo(null);

        assertNull(result);
    }

    @Test
    void allTo_shouldReturnEmptyListWhenInputIsEmpty() {
        Iterable<ProductResponse> result = productConverter.allTo(Collections.emptyList());

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void allFrom_shouldConvertAllResponsesToProducts() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        ProductResponse response1 = new ProductResponse();
        response1.setId(productId1);
        response1.setTitle("Product 1");
        response1.setDescription("Description 1");
        response1.setPrice(new BigDecimal("10.00"));

        ProductResponse response2 = new ProductResponse();
        response2.setId(productId2);
        response2.setTitle("Product 2");
        response2.setDescription("Description 2");
        response2.setPrice(new BigDecimal("20.00"));

        List<ProductResponse> responses = Arrays.asList(response1, response2);

        Iterable<Product> result = productConverter.allFrom(responses);

        assertNotNull(result);
        List<Product> resultList = (List<Product>) result;
        assertEquals(2, resultList.size());
        assertEquals(productId1, resultList.get(0).getId());
        assertEquals("Product 1", resultList.get(0).getTitle());
        assertEquals("Description 1", resultList.get(0).getDescription());
        assertEquals(new BigDecimal("10.00"), resultList.get(0).getPrice());
        assertEquals(productId2, resultList.get(1).getId());
        assertEquals("Product 2", resultList.get(1).getTitle());
    }

    @Test
    void allFrom_shouldReturnNullWhenInputIsNull() {
        Iterable<Product> result = productConverter.allFrom(null);

        assertNull(result);
    }

    @Test
    void allFrom_shouldReturnEmptyListWhenInputIsEmpty() {
        Iterable<Product> result = productConverter.allFrom(Collections.emptyList());

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
}
