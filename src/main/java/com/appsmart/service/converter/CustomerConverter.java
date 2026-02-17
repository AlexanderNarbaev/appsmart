package com.appsmart.service.converter;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.response.CustomerResponse;
import com.appsmart.persistance.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerResponse> {
  @Override
  public CustomerResponse to(Customer input) {
    if (input == null) {
      return null;
    }
    CustomerResponse response = new CustomerResponse();
    response.setId(input.getId());
    response.setTitle(input.getTitle());
    return response;
  }
}
