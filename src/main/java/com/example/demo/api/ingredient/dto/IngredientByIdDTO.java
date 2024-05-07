package com.example.demo.api.ingredient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientByIdDTO extends CreateUpdateIngredientDTO {
    private Long id;
}
