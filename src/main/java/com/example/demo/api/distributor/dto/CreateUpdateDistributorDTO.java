package com.example.demo.api.distributor.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUpdateDistributorDTO {
    private String name;
    private String phone;
    private String email;
    private Long productId;
}
