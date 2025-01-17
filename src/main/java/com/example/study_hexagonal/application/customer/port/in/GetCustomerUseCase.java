package com.example.study_hexagonal.application.customer.port.in;

import com.example.study_hexagonal.domain.customer.Customer;

public interface GetCustomerUseCase {
    Customer getCustomerById(String customerId);
}
