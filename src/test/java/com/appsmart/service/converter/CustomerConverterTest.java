package com.appsmart.service.converter;

import com.appsmart.api.response.CustomerResponse;
import com.appsmart.persistance.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConverterTest {

    private CustomerConverter customerConverter;

    @BeforeEach
    void setUp() {
        customerConverter = new CustomerConverter();
    }

    @Test
    void to_shouldConvertCustomerToCustomerResponse() {
        UUID customerId = UUID.randomUUID();
        String title = "Test Customer";

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setTitle(title);

        CustomerResponse result = customerConverter.to(customer);

        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals(title, result.getTitle());
    }

    @Test
    void to_shouldReturnNullWhenInputIsNull() {
        CustomerResponse result = customerConverter.to(null);

        assertNull(result);
    }

    @Test
    void from_shouldConvertCustomerResponseToCustomer() {
        UUID customerId = UUID.randomUUID();
        String title = "Test Customer";

        CustomerResponse response = new CustomerResponse();
        response.setId(customerId);
        response.setTitle(title);

        Customer result = customerConverter.from(response);

        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals(title, result.getTitle());
    }

    @Test
    void from_shouldReturnNullWhenInputIsNull() {
        Customer result = customerConverter.from(null);

        assertNull(result);
    }

    @Test
    void allTo_shouldConvertAllCustomersToResponses() {
        UUID customerId1 = UUID.randomUUID();
        UUID customerId2 = UUID.randomUUID();

        Customer customer1 = new Customer();
        customer1.setId(customerId1);
        customer1.setTitle("Customer 1");

        Customer customer2 = new Customer();
        customer2.setId(customerId2);
        customer2.setTitle("Customer 2");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        Iterable<CustomerResponse> result = customerConverter.allTo(customers);

        assertNotNull(result);
        List<CustomerResponse> resultList = (List<CustomerResponse>) result;
        assertEquals(2, resultList.size());
        assertEquals(customerId1, resultList.get(0).getId());
        assertEquals("Customer 1", resultList.get(0).getTitle());
        assertEquals(customerId2, resultList.get(1).getId());
        assertEquals("Customer 2", resultList.get(1).getTitle());
    }

    @Test
    void allTo_shouldReturnNullWhenInputIsNull() {
        Iterable<CustomerResponse> result = customerConverter.allTo(null);

        assertNull(result);
    }

    @Test
    void allTo_shouldReturnEmptyListWhenInputIsEmpty() {
        Iterable<CustomerResponse> result = customerConverter.allTo(Collections.emptyList());

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void allFrom_shouldConvertAllResponsesToCustomers() {
        UUID customerId1 = UUID.randomUUID();
        UUID customerId2 = UUID.randomUUID();

        CustomerResponse response1 = new CustomerResponse();
        response1.setId(customerId1);
        response1.setTitle("Customer 1");

        CustomerResponse response2 = new CustomerResponse();
        response2.setId(customerId2);
        response2.setTitle("Customer 2");

        List<CustomerResponse> responses = Arrays.asList(response1, response2);

        Iterable<Customer> result = customerConverter.allFrom(responses);

        assertNotNull(result);
        List<Customer> resultList = (List<Customer>) result;
        assertEquals(2, resultList.size());
        assertEquals(customerId1, resultList.get(0).getId());
        assertEquals("Customer 1", resultList.get(0).getTitle());
        assertEquals(customerId2, resultList.get(1).getId());
        assertEquals("Customer 2", resultList.get(1).getTitle());
    }

    @Test
    void allFrom_shouldReturnNullWhenInputIsNull() {
        Iterable<Customer> result = customerConverter.allFrom(null);

        assertNull(result);
    }

    @Test
    void allFrom_shouldReturnEmptyListWhenInputIsEmpty() {
        Iterable<Customer> result = customerConverter.allFrom(Collections.emptyList());

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
}
