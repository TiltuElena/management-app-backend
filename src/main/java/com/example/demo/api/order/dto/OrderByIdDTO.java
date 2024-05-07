package com.example.demo.api.order.dto;

import com.example.demo.util.dto.IdNameDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrderByIdDTO {
    private Long id;
    private IdNameDTO customer;
    private LocalDate date;
    private List<ProductOrderDTO> products;
    private Integer totalPrice;
}
