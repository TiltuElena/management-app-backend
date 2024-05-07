package com.example.demo.api.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUpdateEmployeeDTO {
    @NotBlank
    @Size(min = 2, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Phone number must contain only numbers")
    @Size(min = 8, max = 15)
    private String phone;
    @NotBlank
    @Email
    @Size(min = 2, max = 255)
    private String email;
    @NotBlank
    @Size(min = 2, max = 255)
    private String address;
    @NotNull
    @Positive
    @Min(1000)
    @Max(100000)
    private Integer salary;
}
