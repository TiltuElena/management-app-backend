package com.example.demo.api.dashboard.service;

import com.example.demo.api.customer.repository.CustomerRepository;
import com.example.demo.api.dashboard.dto.DashboardInfoDTO;
import com.example.demo.api.distributor.repository.DistributorRepository;
import com.example.demo.api.employee.repository.EmployeeRepository;
import com.example.demo.api.order.repository.OrderRepository;
import com.example.demo.api.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final DistributorRepository distributorRepository;
    private final OrderRepository orderRepository;

    public DashboardInfoDTO getDashboardInfo() {
        long customers = customerRepository.count();
        long employees = employeeRepository.count();
        long products = productRepository.count();
        long distributors = distributorRepository.count();
        long orders = orderRepository.count();

        return new DashboardInfoDTO(customers, employees, products, distributors, orders);
    }
}
