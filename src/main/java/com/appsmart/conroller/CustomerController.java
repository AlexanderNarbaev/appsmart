package com.appsmart.conroller;

import com.appsmart.api.request.CreateCustomer;
import com.appsmart.api.request.UpdateCustomer;
import com.appsmart.api.response.CustomerResponse;
import com.appsmart.service.CustomerService;
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
public class CustomerController {
  private final CustomerService customerService;

  @Operation(summary = "Return paginated list of all customers")
  @GetMapping("/customers")
  private Page<CustomerResponse> getAllCustomers(
      @PageableDefault(page = 0, size = 10) @ParameterObject Pageable pageable) {
    return customerService.findAll(pageable);
  }

  @Operation(summary = "Create new customer")
  @PostMapping("/customers")
  private CustomerResponse createCustomer(@RequestBody CreateCustomer customer) {
    return customerService.create(customer);
  }

  @Operation(summary = "Delete customer")
  @DeleteMapping("/customers/{customerId}")
  private void deleteCustomer(@PathVariable UUID customerId) {
    customerService.delete(customerId);
  }

  @Operation(summary = "Return customer by id")
  @GetMapping("/customers/{customerId}")
  private CustomerResponse getCustomer(@PathVariable UUID customerId) {
    return customerService.get(customerId);
  }

  @Operation(summary = "Edit customer")
  @PutMapping("/customers/{customerId}")
  private CustomerResponse updateCustomer(
      @PathVariable UUID customerId, @RequestBody UpdateCustomer customer) {
    return customerService.update(customerId, customer);
  }
}
