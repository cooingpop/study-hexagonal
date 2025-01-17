package com.example.study_hexagonal.infrastructure.adapter.in.web.customer.api.dto;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String name;
    private String email;

    // 생성자, getter, setter
}