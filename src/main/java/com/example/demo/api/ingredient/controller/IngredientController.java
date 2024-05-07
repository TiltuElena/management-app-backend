package com.example.demo.api.ingredient.controller;

import com.example.demo.api.ingredient.dto.IngredientByIdDTO;
import com.example.demo.api.ingredient.dto.CreateUpdateIngredientDTO;
import com.example.demo.api.ingredient.service.IngredientService;
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
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUpdateIngredientDTO createUpdateIngredientDTO) {
        ingredientService.create(createUpdateIngredientDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntityToDTOMappingUtil.mapToDTO(ingredientService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CreateUpdateIngredientDTO createUpdateIngredientDTO) {
        ingredientService.update(id, createUpdateIngredientDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public PaginationResponseDTO<IngredientByIdDTO> getPage(PaginationParameters paginationParameters) {
        return ingredientService.getPage(paginationParameters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dropdown")
    public List<IngredientByIdDTO> getDropdown() {
        return ingredientService.getDropdown();
    }
}
