package com.example.demo.api.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class CreateUpdateOrderDTO {
    @NotNull
    @Positive
    private Long customerId;
    private LocalDate date;
    @NotEmpty
    private List<ProductOrderDTO> products;
}
