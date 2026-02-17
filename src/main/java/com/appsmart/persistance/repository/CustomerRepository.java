package com.appsmart.persistance.repository;

import com.appsmart.persistance.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

  Page<Customer> findAllByDeletedFalse(Pageable pageable);

  Optional<Customer> getCustomerByIdAndDeletedFalse(UUID id);
}
