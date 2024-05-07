package com.example.demo.api.distributor.dto;

import com.example.demo.util.dto.IdNameDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DistributorByIdDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private IdNameDTO product;
}
