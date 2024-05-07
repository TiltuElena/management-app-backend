package com.example.demo.api.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {
    @NotNull
    @Positive
    private Long productId;
    @NotNull
    @Positive
    private Integer quantity;
}
