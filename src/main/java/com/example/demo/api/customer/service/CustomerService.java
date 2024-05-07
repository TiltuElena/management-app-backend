package com.example.demo.api.customer.service;

import com.example.demo.api.customer.repository.CustomerRepository;
import com.example.demo.api.customer.dto.CreateUpdateCustomerDTO;
import com.example.demo.api.customer.dto.CustomerByIdDTO;
import com.example.demo.api.customer.model.Customer;
import com.example.demo.util.dto.IdNameDTO;
import com.example.demo.util.mapping.EntityToDTOMappingUtil;
import com.example.demo.util.pagination.PaginationInfoDTO;
import com.example.demo.util.pagination.PaginationParameters;
import com.example.demo.util.pagination.PaginationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void create(CreateUpdateCustomerDTO createUpdateCustomerDTO) {
        Customer customer = Customer.builder()
                .firstName(createUpdateCustomerDTO.getFirstName())
                .lastName(createUpdateCustomerDTO.getLastName())
                .phone(createUpdateCustomerDTO.getPhone())
                .email(createUpdateCustomerDTO.getEmail())
                .build();

        customerRepository.save(customer);
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " could not be found!"));
    }

    public void update(Long id, CreateUpdateCustomerDTO createUpdateCustomerDTO) {
        Customer customer = getById(id);
        customer.setFirstName(createUpdateCustomerDTO.getFirstName());
        customer.setLastName(createUpdateCustomerDTO.getLastName());
        customer.setPhone(createUpdateCustomerDTO.getPhone());
        customer.setEmail(createUpdateCustomerDTO.getEmail());

        customerRepository.save(customer);
    }

    public PaginationResponseDTO<CustomerByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Customer> page = customerRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<CustomerByIdDTO> customersList = page.getContent().stream()
                .map(EntityToDTOMappingUtil::mapToDTO)
                .toList();

        PaginationInfoDTO paginationInfo = new PaginationInfoDTO(
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );

        return new PaginationResponseDTO<>(customersList, paginationInfo);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<IdNameDTO> getDropdown() {
        List<Customer> customers = customerRepository.findAll();
        List<IdNameDTO> customersForDropdown = new ArrayList<>();

        for (Customer customer : customers) {
            IdNameDTO customerForDTOList = new IdNameDTO(customer.getId(), customer.getFirstName() + " " + customer.getLastName());
            customersForDropdown.add(customerForDTOList);
        }

        return customersForDropdown;
    }

}
