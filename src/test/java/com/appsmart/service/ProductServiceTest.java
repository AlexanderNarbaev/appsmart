package com.appsmart.service;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.request.CreateProduct;
import com.appsmart.api.request.UpdateProduct;
import com.appsmart.api.response.ProductResponse;
import com.appsmart.persistance.entity.Product;
import com.appsmart.persistance.repository.CustomerRepository;
import com.appsmart.persistance.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {
  @Mock ProductRepository productRepository;
  @Mock CustomerRepository customerRepository;
  @Mock Converter<Product, ProductResponse> converter;
  @InjectMocks ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetAllByCustomerId() {
    when(productRepository.findAllByCustomerIdAndDeletedFalse(any(), any())).thenReturn(null);

    Page<ProductResponse> result = productService.getAllByCustomerId(null, null);
    Assertions.assertEquals(null, result);
  }

  @Test
  void testCreate() {
    when(customerRepository.getCustomerByIdAndDeletedFalse(any())).thenReturn(null);
    //        when(converter.to(any())).thenReturn(new B());

    ProductResponse result = productService.create(null, new CreateProduct());
    Assertions.assertEquals(new ProductResponse(), result);
  }

  @Test
  void testDelete() {
    when(productRepository.getProductByIdAndDeletedFalse(any())).thenReturn(null);

    productService.delete(null);
  }

  @Test
  void testGet() {
    when(productRepository.getProductByIdAndDeletedFalse(any())).thenReturn(null);
    //        when(converter.to(any())).thenReturn(new B());

    ProductResponse result = productService.get(null);
    Assertions.assertEquals(new ProductResponse(), result);
  }

  @Test
  void testUpdate() {
    when(productRepository.getProductByIdAndDeletedFalse(any())).thenReturn(null);
    //        when(converter.to(any())).thenReturn(new B());

    ProductResponse result = productService.update(null, new UpdateProduct());
    Assertions.assertEquals(new ProductResponse(), result);
  }
}

// Generated with love by TestMe :) Please report issues and submit feature requests at:
// http://weirddev.com/forum#!/testme
