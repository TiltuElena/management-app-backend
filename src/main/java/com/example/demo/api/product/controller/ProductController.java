package com.example.demo.api.product.controller;

import com.example.demo.api.product.dto.CreateUpdateProductDTO;
import com.example.demo.api.product.dto.ProductByIdDTO;
import com.example.demo.api.product.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateProductDTO createUpdateProductDTO) {
        productService.create(createUpdateProductDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(productService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateProductDTO createUpdateProductDTO) {
        productService.update(id, createUpdateProductDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public PaginationResponseDTO<ProductByIdDTO> getPage(PaginationParameters paginationParameters) {
        return productService.getPage(paginationParameters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dropdown")
    public List<IdNameDTO> getDropdown() {
        return productService.getDropdown();
    }
}
