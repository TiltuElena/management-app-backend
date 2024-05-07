package com.example.demo.api.order.controller;

import com.example.demo.api.order.dto.CreateUpdateOrderDTO;
import com.example.demo.api.order.dto.OrderByIdDTO;
import com.example.demo.api.order.service.OrderService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateOrderDTO createUpdateOrderDTO) {
        orderService.create(createUpdateOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public PaginationResponseDTO<OrderByIdDTO> getPage(PaginationParameters paginationParameters) {
        return orderService.getPage(paginationParameters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(orderService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateOrderDTO createUpdateOrderDTO) {
        orderService.update(id, createUpdateOrderDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
