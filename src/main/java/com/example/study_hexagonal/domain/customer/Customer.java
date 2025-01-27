package com.example.study_hexagonal.domain.customer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String email;

    // 생성자, getter, setter 생략
}