package com.appsmart.persistance.repository;

import com.appsmart.persistance.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

  Page<Product> findAllByCustomerIdAndDeletedFalse(UUID customerId, Pageable pageable);

  Optional<Product> getProductByIdAndDeletedFalse(UUID id);
}
