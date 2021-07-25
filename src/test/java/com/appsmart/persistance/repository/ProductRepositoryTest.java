package com.appsmart.persistance.repository;

import com.appsmart.persistance.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class ProductRepositoryTest {
  @Autowired private ProductRepository repository;

  private static UUID customerId = UUID.randomUUID();

  @Test
  void shouldFindByIdAndIsDeletedFalse() {

    Optional<Product> productByIdAndDeletedFalse =
        repository.getProductByIdAndDeletedFalse(UUID.randomUUID());
    Assertions.assertTrue(productByIdAndDeletedFalse.isEmpty());
    Product product = new Product();
    product.setDeleted(false);
    product.setCustomerId(customerId);
    product.setTitle("TEST");
    product.setPrice(BigDecimal.ZERO);
    product.setId(UUID.randomUUID());
    product.setCreatedAt(LocalDateTime.now());
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    productByIdAndDeletedFalse = repository.getProductByIdAndDeletedFalse(product.getId());
    Assertions.assertTrue(productByIdAndDeletedFalse.isPresent());
    product.setDeleted(true);
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    productByIdAndDeletedFalse = repository.getProductByIdAndDeletedFalse(product.getId());
    Assertions.assertTrue(productByIdAndDeletedFalse.isEmpty());
  }

  @Test
  void shouldFindAllAndIsDeletedFalse() {
    Page<Product> allByCustomerIdAndDeletedFalse =
        repository.findAllByCustomerIdAndDeletedFalse(customerId, Pageable.ofSize(1));
    Assertions.assertTrue(allByCustomerIdAndDeletedFalse.isEmpty());
    Product product = new Product();
    product.setDeleted(false);
    product.setTitle("TEST");
    product.setCustomerId(customerId);
    product.setId(UUID.randomUUID());
    product.setCreatedAt(LocalDateTime.now());
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    allByCustomerIdAndDeletedFalse =
        repository.findAllByCustomerIdAndDeletedFalse(customerId, Pageable.ofSize(1));
    Assertions.assertFalse(allByCustomerIdAndDeletedFalse.isEmpty());
    product.setDeleted(true);
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    allByCustomerIdAndDeletedFalse =
        repository.findAllByCustomerIdAndDeletedFalse(customerId, Pageable.ofSize(1));
    Assertions.assertTrue(allByCustomerIdAndDeletedFalse.isEmpty());
    product = new Product();
    product.setDeleted(false);
    product.setCustomerId(customerId);
    product.setTitle("TEST");
    product.setId(UUID.randomUUID());
    product.setCreatedAt(LocalDateTime.now());
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    product = new Product();
    product.setDeleted(false);
    product.setCustomerId(customerId);
    product.setTitle("TEST");
    product.setId(UUID.randomUUID());
    product.setCreatedAt(LocalDateTime.now());
    product.setModifiedAt(LocalDateTime.now());
    product = repository.saveAndFlush(product);
    allByCustomerIdAndDeletedFalse =
        repository.findAllByCustomerIdAndDeletedFalse(customerId, Pageable.ofSize(1));
    Assertions.assertFalse(allByCustomerIdAndDeletedFalse.isEmpty());
    Assertions.assertEquals(2L, allByCustomerIdAndDeletedFalse.getTotalElements());
    Assertions.assertEquals(2L, allByCustomerIdAndDeletedFalse.getTotalPages());
  }
}
