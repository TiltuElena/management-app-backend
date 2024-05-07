package com.example.demo.api.order.service;

import com.example.demo.api.customer.model.Customer;
import com.example.demo.api.customer.service.CustomerService;
import com.example.demo.api.order.dto.CreateUpdateOrderDTO;
import com.example.demo.api.order.dto.OrderByIdDTO;
import com.example.demo.api.order.dto.ProductOrderDTO;
import com.example.demo.api.order.model.Order;
import com.example.demo.api.order.model.OrderProduct;
import com.example.demo.api.order.repository.OrderProductRepository;
import com.example.demo.api.order.repository.OrderRepository;
import com.example.demo.api.product.dto.ProductByIdDTO;
import com.example.demo.api.product.model.Product;
import com.example.demo.api.product.service.ProductService;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderProductRepository orderProductRepository;

    public void create(CreateUpdateOrderDTO createUpdateOrderDTO) {
        Order order = new Order();
        orderRepository.save(order);

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (ProductOrderDTO productOrderDTO : createUpdateOrderDTO.getProducts()) {
            OrderProduct orderProduct = new OrderProduct(order, productService.getById(productOrderDTO.getProductId()), productOrderDTO.getQuantity());
            orderProducts.add(orderProduct);
        }

        orderProductRepository.saveAll(orderProducts);

        Customer customer = customerService.getById(createUpdateOrderDTO.getCustomerId());
        order.setCustomer(customer);
        order.setOrderProducts(orderProducts);
        order.setTotalPrice(computeTotalPrice(orderProducts));
        order.setDate(createUpdateOrderDTO.getDate());

        orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id " + id + " could not be found"));
    }

    public void update(Long id, CreateUpdateOrderDTO createUpdateOrderDTO) {
        Order order = getById(id);

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (ProductOrderDTO productOrderDTO : createUpdateOrderDTO.getProducts()) {
            OrderProduct orderProduct = new OrderProduct(order, productService.getById(productOrderDTO.getProductId()), productOrderDTO.getQuantity());
            orderProducts.add(orderProduct);
        }

        Customer customer = customerService.getById(createUpdateOrderDTO.getCustomerId());
        order.setCustomer(customer);
        order.setOrderProducts(orderProducts);
        order.setTotalPrice(computeTotalPrice(orderProducts));
        order.setDate(createUpdateOrderDTO.getDate());

        orderRepository.save(order);
    }

    public PaginationResponseDTO<OrderByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Order> page = orderRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<OrderByIdDTO> orders = page.getContent().stream()
                .map(EntityToDTOMappingUtil::mapToDTO)
                .toList();

        PaginationInfoDTO paginationInfo = new PaginationInfoDTO(
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );

        return new PaginationResponseDTO<>(orders, paginationInfo);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    private int computeTotalPrice(List<OrderProduct> orderProducts) {

        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getProduct().getPrice() * orderProduct.getQuantity();
        }
        return totalPrice;
    }
}
