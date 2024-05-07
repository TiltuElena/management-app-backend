package com.example.demo.api.employee.service;

import com.example.demo.api.employee.dto.EmployeeByIdDTO;
import com.example.demo.api.employee.model.Employee;
import com.example.demo.api.employee.repository.EmployeeRepository;
import com.example.demo.api.employee.dto.CreateUpdateEmployeeDTO;
import com.example.demo.util.mapping.EntityToDTOMappingUtil;
import com.example.demo.util.pagination.PaginationInfoDTO;
import com.example.demo.util.pagination.PaginationParameters;
import com.example.demo.util.pagination.PaginationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void create(CreateUpdateEmployeeDTO createUpdateEmployeeDTO) {
        Employee employee = Employee.builder()
                .firstName(createUpdateEmployeeDTO.getFirstName())
                .lastName(createUpdateEmployeeDTO.getLastName())
                .phone(createUpdateEmployeeDTO.getPhone())
                .email(createUpdateEmployeeDTO.getEmail())
                .address(createUpdateEmployeeDTO.getAddress())
                .salary(createUpdateEmployeeDTO.getSalary())
                .build();

        employeeRepository.save(employee);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id " + id + " could not be found!"));
    }

    public void update(Long id, CreateUpdateEmployeeDTO createUpdateEmployeeDTO) {
        Employee employee = getById(id);
        employee.setFirstName(createUpdateEmployeeDTO.getFirstName());
        employee.setLastName(createUpdateEmployeeDTO.getLastName());
        employee.setPhone(createUpdateEmployeeDTO.getPhone());
        employee.setEmail(createUpdateEmployeeDTO.getEmail());
        employee.setAddress(createUpdateEmployeeDTO.getAddress());
        employee.setSalary(createUpdateEmployeeDTO.getSalary());

        employeeRepository.save(employee);
    }

    public PaginationResponseDTO<EmployeeByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Employee> page = employeeRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<EmployeeByIdDTO> employeesList = page.getContent().stream()
                .map(EntityToDTOMappingUtil::mapToDTO)
                .toList();

        PaginationInfoDTO paginationInfo = new PaginationInfoDTO(
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );

        return new PaginationResponseDTO<>(employeesList, paginationInfo);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
