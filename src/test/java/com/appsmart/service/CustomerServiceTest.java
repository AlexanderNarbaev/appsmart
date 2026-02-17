package com.appsmart.service;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.request.CreateCustomer;
import com.appsmart.api.request.UpdateCustomer;
import com.appsmart.api.response.CustomerResponse;
import com.appsmart.persistance.entity.Customer;
import com.appsmart.persistance.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
  @Mock CustomerRepository customerRepository;
  @Mock Converter<Customer, CustomerResponse> converter;
  @InjectMocks CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testFindAll() {
    when(customerRepository.findAllByDeletedFalse(any())).thenReturn(null);

    Page<CustomerResponse> result = customerService.findAll(null);
    Assertions.assertEquals(null, result);
  }

  @Test
  void testCreate() {
    //        when(converter.to(any())).thenReturn(new B());

    CustomerResponse result = customerService.create(new CreateCustomer());
    Assertions.assertEquals(new CustomerResponse(), result);
  }

  @Test
  void testDelete() {
    when(customerRepository.getCustomerByIdAndDeletedFalse(any())).thenReturn(null);

    customerService.delete(null);
  }

  @Test
  void testGet() {
    when(customerRepository.getCustomerByIdAndDeletedFalse(any())).thenReturn(null);
    //        when(converter.to(any())).thenReturn(new B());

    CustomerResponse result = customerService.get(null);
    Assertions.assertEquals(new CustomerResponse(), result);
  }

  @Test
  void testUpdate() {
    when(customerRepository.getCustomerByIdAndDeletedFalse(any())).thenReturn(null);
    //        when(converter.to(any())).thenReturn(new B());

    CustomerResponse result = customerService.update(null, new UpdateCustomer());
    Assertions.assertEquals(new CustomerResponse(), result);
  }
}

// Generated with love by TestMe :) Please report issues and submit feature requests at:
// http://weirddev.com/forum#!/testme
