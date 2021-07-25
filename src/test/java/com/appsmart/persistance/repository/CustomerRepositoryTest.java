package com.appsmart.persistance.repository;

import com.appsmart.persistance.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class CustomerRepositoryTest {

  @Autowired private CustomerRepository repository;

  @Test
  void shouldFindByIdAndIsDeletedFalse() {
    Optional<Customer> customerByIdAndDeletedFalse =
        repository.getCustomerByIdAndDeletedFalse(UUID.randomUUID());
    Assertions.assertTrue(customerByIdAndDeletedFalse.isEmpty());
    Customer customer = new Customer();
    customer.setDeleted(false);
    customer.setTitle("TEST");
    customer.setId(UUID.randomUUID());
    customer.setCreatedAt(LocalDateTime.now());
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    customerByIdAndDeletedFalse = repository.getCustomerByIdAndDeletedFalse(customer.getId());
    Assertions.assertTrue(customerByIdAndDeletedFalse.isPresent());
    customer.setDeleted(true);
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    customerByIdAndDeletedFalse = repository.getCustomerByIdAndDeletedFalse(customer.getId());
    Assertions.assertTrue(customerByIdAndDeletedFalse.isEmpty());
  }

  @Test
  void shouldFindAllAndIsDeletedFalse() {
    Page<Customer> allByDeletedFalse = repository.findAllByDeletedFalse(Pageable.ofSize(1));
    Assertions.assertTrue(allByDeletedFalse.isEmpty());
    Customer customer = new Customer();
    customer.setDeleted(false);
    customer.setTitle("TEST");
    customer.setId(UUID.randomUUID());
    customer.setCreatedAt(LocalDateTime.now());
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    allByDeletedFalse = repository.findAllByDeletedFalse(Pageable.ofSize(1));
    Assertions.assertFalse(allByDeletedFalse.isEmpty());
    customer.setDeleted(true);
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    allByDeletedFalse = repository.findAllByDeletedFalse(Pageable.ofSize(1));
    Assertions.assertTrue(allByDeletedFalse.isEmpty());
    customer = new Customer();
    customer.setDeleted(false);
    customer.setTitle("TEST");
    customer.setId(UUID.randomUUID());
    customer.setCreatedAt(LocalDateTime.now());
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    customer = new Customer();
    customer.setDeleted(false);
    customer.setTitle("TEST");
    customer.setId(UUID.randomUUID());
    customer.setCreatedAt(LocalDateTime.now());
    customer.setModifiedAt(LocalDateTime.now());
    customer = repository.saveAndFlush(customer);
    allByDeletedFalse = repository.findAllByDeletedFalse(Pageable.ofSize(1));
    Assertions.assertFalse(allByDeletedFalse.isEmpty());
    Assertions.assertEquals(2L, allByDeletedFalse.getTotalElements());
    Assertions.assertEquals(2L, allByDeletedFalse.getTotalPages());
  }
}
