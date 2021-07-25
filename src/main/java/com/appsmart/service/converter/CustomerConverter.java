package com.appsmart.service.converter;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.response.CustomerResponse;
import com.appsmart.persistance.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomerConverter implements Converter<Customer, CustomerResponse> {
  @Override
  public Customer from(CustomerResponse input) {
    if (input != null) {
      Customer customer = new Customer();
      customer.setId(input.getId());
      customer.setTitle(input.getTitle());
      return customer;
    } else {
      return null;
    }
  }

  @Override
  public Iterable<Customer> allFrom(Iterable<CustomerResponse> input) {
    if (input != null) {
      Collection<Customer> cache = new ArrayList<>();
      input.forEach(customerResponse -> cache.add(from(customerResponse)));
      return cache;
    } else {
      return null;
    }
  }

  @Override
  public CustomerResponse to(Customer input) {
    if (input != null) {
      CustomerResponse customerResponse = new CustomerResponse();
      customerResponse.setId(input.getId());
      customerResponse.setTitle(input.getTitle());
      return customerResponse;
    } else {
      return null;
    }
  }

  @Override
  public Iterable<CustomerResponse> allTo(Iterable<Customer> input) {
    if (input != null) {
      Collection<CustomerResponse> cache = new ArrayList<>();
      input.forEach(customerResponse -> cache.add(to(customerResponse)));
      return cache;
    } else {
      return null;
    }
  }
}
