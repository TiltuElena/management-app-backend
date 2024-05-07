package com.example.demo.api.ingredient.service;

import com.example.demo.api.ingredient.dto.IngredientByIdDTO;
import com.example.demo.api.ingredient.model.Ingredient;
import com.example.demo.api.ingredient.repository.IngredientRepository;
import com.example.demo.api.ingredient.dto.CreateUpdateIngredientDTO;
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
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public void create(CreateUpdateIngredientDTO createUpdateIngredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(createUpdateIngredientDTO.getName());

        ingredientRepository.save(ingredient);
    }

    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient with id " + id + " could not be found!"));
    }

    public void update(Long id, CreateUpdateIngredientDTO createUpdateIngredientDTO) {
        Ingredient ingredient = getById(id);
        ingredient.setName(createUpdateIngredientDTO.getName());

        ingredientRepository.save(ingredient);
    }

    public PaginationResponseDTO<IngredientByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Ingredient> page = ingredientRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<IngredientByIdDTO> ingredients = page.getContent().stream()
                .map(EntityToDTOMappingUtil::mapToDTO)
                .toList();

        PaginationInfoDTO paginationInfo = new PaginationInfoDTO(
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );

        return new PaginationResponseDTO<>(ingredients, paginationInfo);
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public List<IngredientByIdDTO> getDropdown() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientByIdDTO> ingredientsList = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            IngredientByIdDTO ingredientByIdDTO = EntityToDTOMappingUtil.mapToDTO(ingredient);
            ingredientsList.add(ingredientByIdDTO);
        }

        return ingredientsList;
    }
}
