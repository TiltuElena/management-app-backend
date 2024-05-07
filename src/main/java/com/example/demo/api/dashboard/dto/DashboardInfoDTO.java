package com.example.demo.api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardInfoDTO {
    private long customers;
    private long employees;
    private long products;
    private long distributors;
    private long orders;
}
