package com.appsmart.service.converter;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.response.ProductResponse;
import com.appsmart.persistance.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductResponse> {
  @Override
  public ProductResponse to(Product input) {
    if (input == null) {
      return null;
    }
    ProductResponse response = new ProductResponse();
    response.setId(input.getId());
    response.setTitle(input.getTitle());
    response.setDescription(input.getDescription());
    response.setPrice(input.getPrice());
    return response;
  }
}
