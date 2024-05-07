package com.example.demo.api.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateUpdateProductDTO {
    private String name;
    private String description;
    private Integer price;
    private List<Long> ingredients;
}
