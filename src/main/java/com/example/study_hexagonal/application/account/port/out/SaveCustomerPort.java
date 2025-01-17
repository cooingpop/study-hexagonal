package com.example.study_hexagonal.application.account.port.out;

import com.example.study_hexagonal.domain.customer.Customer;

public interface SaveCustomerPort {
    /**
     * 고객 정보를 저장하고 저장된 고객 객체를 반환합니다.
     * @param customer 저장할 고객 정보
     * @return 저장된 고객 객체
     */
    Customer saveCustomer(Customer customer);
}