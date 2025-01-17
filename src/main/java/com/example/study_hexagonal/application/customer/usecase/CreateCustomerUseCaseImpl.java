package com.example.study_hexagonal.application.customer.usecase;

import com.example.study_hexagonal.application.account.port.out.SaveCustomerPort;
import com.example.study_hexagonal.application.customer.port.in.CreateCustomerUseCase;
import com.example.study_hexagonal.domain.customer.Customer;
import com.example.study_hexagonal.domain.customer.CustomerInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final SaveCustomerPort saveCustomerPort;

    public CreateCustomerUseCaseImpl(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public Customer createCustomer(CustomerInfo customerInfo) {
        // 새로운 고객 ID 생성
        String customerId = UUID.randomUUID().toString();

        // Customer 객체 생성
        Customer newCustomer = new Customer(
                customerId,
                customerInfo.getName(),
                customerInfo.getEmail()
                // 필요한 경우 추가 필드 설정
        );

        // 고객 정보 저장
        return saveCustomerPort.saveCustomer(newCustomer);
    }
}