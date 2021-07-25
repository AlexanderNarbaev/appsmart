package com.appsmart.service.converter;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.response.ProductResponse;
import com.appsmart.persistance.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProductConverter implements Converter<Product, ProductResponse> {
  @Override
  public Product from(ProductResponse input) {
    if (input != null) {
      Product product = new Product();
      product.setId(input.getId());
      product.setTitle(input.getTitle());
      product.setDescription(input.getDescription());
      product.setPrice(input.getPrice());
      return product;
    } else {
      return null;
    }
  }

  @Override
  public Iterable<Product> allFrom(Iterable<ProductResponse> input) {
    if (input != null) {
      Collection<Product> cache = new ArrayList<>();
      input.forEach(productResponse -> cache.add(from(productResponse)));
      return cache;
    } else {
      return null;
    }
  }

  @Override
  public ProductResponse to(Product input) {
    if (input != null) {
      ProductResponse productResponse = new ProductResponse();
      productResponse.setId(input.getId());
      productResponse.setTitle(input.getTitle());
      productResponse.setDescription(input.getDescription());
      productResponse.setPrice(input.getPrice());
      return productResponse;
    } else {
      return null;
    }
  }

  @Override
  public Iterable<ProductResponse> allTo(Iterable<Product> input) {
    if (input != null) {
      Collection<ProductResponse> cache = new ArrayList<>();
      input.forEach(productResponse -> cache.add(to(productResponse)));
      return cache;
    } else {
      return null;
    }
  }
}
