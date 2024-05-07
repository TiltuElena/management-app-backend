package com.example.demo.api.product.service;

import com.example.demo.api.ingredient.model.Ingredient;
import com.example.demo.api.ingredient.repository.IngredientRepository;
import com.example.demo.api.product.dto.CreateUpdateProductDTO;
import com.example.demo.api.product.dto.ProductByIdDTO;
import com.example.demo.api.product.model.Product;
import com.example.demo.api.product.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public void create(CreateUpdateProductDTO createUpdateProductDTO) {
        List<Ingredient> ingredients = ingredientRepository.findAllById(createUpdateProductDTO.getIngredients());
        Product product = Product.builder()
                .name(createUpdateProductDTO.getName())
                .description(createUpdateProductDTO.getDescription())
                .price(createUpdateProductDTO.getPrice())
                .ingredients(ingredients)
                .build();

        productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " could not be found"));
    }

    public void update(Long id, CreateUpdateProductDTO createUpdateProductDTO) {
        Product product = getById(id);
        List<Ingredient> ingredients = ingredientRepository.findAllById(createUpdateProductDTO.getIngredients());

        product.setName(createUpdateProductDTO.getName());
        product.setDescription(createUpdateProductDTO.getDescription());
        product.setPrice(createUpdateProductDTO.getPrice());
        product.setIngredients(ingredients);

        productRepository.save(product);
    }

    public PaginationResponseDTO<ProductByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Product> page = productRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<ProductByIdDTO> ingredients = page.getContent().stream()
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
        productRepository.deleteById(id);
    }

    public List<IdNameDTO> getDropdown() {
        List<Product> products = productRepository.findAll();
        List<IdNameDTO> productsForDropdown = new ArrayList<>();

        for (Product product : products) {
            IdNameDTO productForDropdown = new IdNameDTO(product.getId(), product.getName());
            productsForDropdown.add(productForDropdown);
        }

        return productsForDropdown;
    }

}
