package com.example.demo.api.employee.controller;

import com.example.demo.api.employee.dto.EmployeeByIdDTO;
import com.example.demo.util.mapping.EntityToDTOMappingUtil;
import com.example.demo.api.employee.dto.CreateUpdateEmployeeDTO;
import com.example.demo.api.employee.service.EmployeeService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateEmployeeDTO createUpdateCustomerDTO) {
        employeeService.create(createUpdateCustomerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(employeeService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateEmployeeDTO createUpdateEmployeeDTO) {
        employeeService.update(id, createUpdateEmployeeDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public PaginationResponseDTO<EmployeeByIdDTO> getPage(PaginationParameters paginationParameters) {
        return employeeService.getPage(paginationParameters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
