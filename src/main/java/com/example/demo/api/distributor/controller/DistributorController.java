package com.example.demo.api.distributor.controller;

import com.example.demo.api.distributor.dto.CreateUpdateDistributorDTO;
import com.example.demo.api.distributor.dto.DistributorByIdDTO;
import com.example.demo.api.distributor.service.DistributorService;
import com.example.demo.api.product.dto.CreateUpdateProductDTO;
import com.example.demo.api.product.dto.ProductByIdDTO;
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
@RequestMapping("/distributors")
public class DistributorController {

    private final DistributorService distributorService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateDistributorDTO createUpdateDistributorDTO) {
        distributorService.create(createUpdateDistributorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistributorByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(distributorService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateDistributorDTO createUpdateDistributorDTO) {
        distributorService.update(id, createUpdateDistributorDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public PaginationResponseDTO<DistributorByIdDTO> getPage(PaginationParameters paginationParameters) {
        return distributorService.getPage(paginationParameters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        distributorService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
