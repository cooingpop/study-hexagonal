package com.example.study_hexagonal.infrastructure.adapter.in.web.customer.api;

import com.example.study_hexagonal.application.customer.port.in.CreateCustomerUseCase;
import com.example.study_hexagonal.application.customer.port.in.GetCustomerUseCase;
import com.example.study_hexagonal.domain.customer.Customer;
import com.example.study_hexagonal.domain.customer.CustomerInfo;
import com.example.study_hexagonal.infrastructure.adapter.in.web.customer.api.dto.CustomerRequestDto;
import com.example.study_hexagonal.infrastructure.adapter.in.web.customer.api.dto.CustomerResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerApiController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto requestDto) {
        Customer customer = createCustomerUseCase.createCustomer(mapToCustomerInfo(requestDto));
        return ResponseEntity.ok(mapToResponseDto(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable String customerId) {
        Customer customer = getCustomerUseCase.getCustomerById(customerId);
        return ResponseEntity.ok(mapToResponseDto(customer));
    }

    private CustomerInfo mapToCustomerInfo(CustomerRequestDto dto) {
        return new CustomerInfo(dto.getName(), dto.getEmail());
    }

    private CustomerResponseDto mapToResponseDto(Customer customer) {
        return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail());
    }
}