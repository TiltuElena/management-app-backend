package com.example.demo.api.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerByIdDTO extends CreateUpdateCustomerDTO {
    private Long id;
}
