package com.appsmart.service;

import com.appsmart.api.converter.Converter;
import com.appsmart.api.request.CreateProduct;
import com.appsmart.api.request.UpdateProduct;
import com.appsmart.api.response.ProductResponse;
import com.appsmart.persistance.entity.Customer;
import com.appsmart.persistance.entity.Product;
import com.appsmart.persistance.repository.CustomerRepository;
import com.appsmart.persistance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final Converter<Product, ProductResponse> converter;

  public Page<ProductResponse> getAllByCustomerId(UUID customerId, Pageable pageable) {
    return productRepository
        .findAllByCustomerIdAndDeletedFalse(customerId, pageable)
        .map(converter::to);
  }

  public ProductResponse create(UUID customerId, CreateProduct product) {
    Optional<Customer> customerById = customerRepository.getCustomerByIdAndDeletedFalse(customerId);
    if (customerById.isPresent()) {
      Product newProduct = new Product();
      newProduct.setId(UUID.randomUUID());
      newProduct.setCustomerId(customerId);
      newProduct.setTitle(product.getTitle());
      newProduct.setDescription(product.getDescription());
      newProduct.setPrice(product.getPrice());
      productRepository.save(newProduct);
      return converter.to(newProduct);
    } else {
      return null;
    }
  }

  public void delete(UUID productId) {
    productRepository
        .getProductByIdAndDeletedFalse(productId)
        .ifPresent(
            product -> {
              product.setDeleted(true);
              product.setModifiedAt(LocalDateTime.now());
              productRepository.save(product);
            });
  }

  public ProductResponse get(UUID productId) {
    return converter.to(productRepository.getProductByIdAndDeletedFalse(productId).orElse(null));
  }

  public ProductResponse update(UUID productId, UpdateProduct product) {
    Optional<Product> byId = productRepository.getProductByIdAndDeletedFalse(productId);
    if (byId.isPresent()) {
      Product productFromDB = byId.get();
      productFromDB.setTitle(product.getTitle());
      productFromDB.setDescription(product.getDescription());
      productFromDB.setPrice(product.getPrice());
      productFromDB.setModifiedAt(LocalDateTime.now());
      productRepository.save(productFromDB);
      return converter.to(productFromDB);
    }
    return null;
  }
}
