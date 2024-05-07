package com.example.demo.api.customer.controller;

import com.example.demo.api.customer.dto.CreateUpdateCustomerDTO;
import com.example.demo.api.customer.dto.CustomerByIdDTO;
import com.example.demo.api.customer.service.CustomerService;
import com.example.demo.util.dto.IdNameDTO;
import com.example.demo.util.mapping.EntityToDTOMappingUtil;
import com.example.demo.util.pagination.PaginationParameters;
import com.example.demo.util.pagination.PaginationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateCustomerDTO createUpdateCustomerDTO) {
        customerService.create(createUpdateCustomerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(customerService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateCustomerDTO createUpdateCustomerDTO) {
        customerService.update(id, createUpdateCustomerDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public PaginationResponseDTO<CustomerByIdDTO> getPage(PaginationParameters paginationParameters) {
        return customerService.getPage(paginationParameters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dropdown")
    public List<IdNameDTO> getDropdown() {
        return customerService.getDropdown();
    }

}
