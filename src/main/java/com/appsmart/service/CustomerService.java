package com.appsmart.service;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.request.CreateCustomer;
import com.appsmart.api.request.UpdateCustomer;
import com.appsmart.api.response.CustomerResponse;
import com.appsmart.persistance.entity.Customer;
import com.appsmart.persistance.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final Converter<Customer, CustomerResponse> converter;

  public Page<CustomerResponse> findAll(Pageable pageable) {
    Page<Customer> allByDeletedFalse = customerRepository.findAllByDeletedFalse(pageable);
    return allByDeletedFalse.map(converter::to);
  }

  public CustomerResponse create(CreateCustomer customer) {
    Customer newCustomer = new Customer();
    newCustomer.setId(UUID.randomUUID());
    newCustomer.setTitle(customer.getTitle());
    customerRepository.save(newCustomer);
    return converter.to(newCustomer);
  }

  public void delete(UUID customerId) {
    customerRepository
        .getCustomerByIdAndDeletedFalse(customerId)
        .ifPresent(
            customer -> {
              customer.setDeleted(true);
              customer.setModifiedAt(LocalDateTime.now());
              customerRepository.save(customer);
            });
  }

  public CustomerResponse get(UUID customerId) {
    return converter.to(customerRepository.getCustomerByIdAndDeletedFalse(customerId).orElse(null));
  }

  public CustomerResponse update(UUID customerId, UpdateCustomer customer) {
    Optional<Customer> byId = customerRepository.getCustomerByIdAndDeletedFalse(customerId);
    if (byId.isPresent()) {
      Customer customerFromDB = byId.get();
      customerFromDB.setTitle(customer.getTitle());
      customerFromDB.setModifiedAt(LocalDateTime.now());
      customerRepository.save(customerFromDB);
      return converter.to(customerFromDB);
    }
    return null;
  }
}
