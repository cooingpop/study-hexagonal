package com.example.study_hexagonal.application.customer.usecase;

import com.example.study_hexagonal.application.customer.port.in.GetCustomerUseCase;
import com.example.study_hexagonal.domain.customer.Customer;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {

    @Override
    public Customer getCustomerById(String customerId) {
        return null;
    }
}
