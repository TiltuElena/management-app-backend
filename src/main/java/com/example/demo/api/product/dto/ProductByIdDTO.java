package com.example.demo.api.product.dto;

import com.example.demo.util.dto.IdNameDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductByIdDTO {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private List<IdNameDTO> ingredients;
}
