package com.appsmart.conroller;

import com.appsmart.api.request.CreateProduct;
import com.appsmart.api.request.UpdateProduct;
import com.appsmart.api.response.ProductResponse;
import com.appsmart.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @Operation(summary = "Returns paginated list of all customer products")
  @GetMapping("/customers/{customerId}/products")
  private Page<ProductResponse> getAllProducts(
      @PathVariable UUID customerId,
      @PageableDefault(page = 0, size = 10) @ParameterObject Pageable pageable) {
    return productService.getAllByCustomerId(customerId, pageable);
  }

  @Operation(summary = "Create new product for customer")
  @PostMapping("/customers/{customerId}/")
  private ProductResponse createProduct(
      @PathVariable UUID customerId, @RequestBody CreateProduct product) {
    return productService.create(customerId, product);
  }

  @Operation(summary = "Delete product")
  @DeleteMapping("/products/{productId}")
  private void deleteProduct(@PathVariable UUID productId) {
    productService.delete(productId);
  }

  @Operation(summary = "Returns product by id")
  @GetMapping("/products/{productId}")
  private ProductResponse getProduct(@PathVariable UUID productId) {
    return productService.get(productId);
  }

  @Operation(summary = "Edit product")
  @PutMapping("/products/{productId}")
  private ProductResponse updateProduct(
      @PathVariable UUID productId, @RequestBody UpdateProduct product) {
    return productService.update(productId, product);
  }
}
