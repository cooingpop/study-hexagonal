package com.example.study_hexagonal.application.customer.port.in;

import com.example.study_hexagonal.domain.customer.Customer;
import com.example.study_hexagonal.domain.customer.CustomerInfo;

public interface CreateCustomerUseCase {
    Customer createCustomer(CustomerInfo customerInfo);
}
