package com.example.demo.api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateCustomerDTO {
    @NotBlank
    @Size(min = 2, max = 100, message = "firstName length should be between 2 and 100")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 100, message = "lastName length should be between 2 and 100")
    private String lastName;
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Phone number must contain only numbers")
    @Size(min = 8, max = 15)
    private String phone;
    @NotBlank
    @Email(message = "Not a valid email")
    private String email;
}
