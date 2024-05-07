package com.example.demo.api.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeByIdDTO extends CreateUpdateEmployeeDTO {
    private Long id;
}
