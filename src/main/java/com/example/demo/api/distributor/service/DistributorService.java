package com.example.demo.api.distributor.service;

import com.example.demo.api.distributor.dto.CreateUpdateDistributorDTO;
import com.example.demo.api.distributor.dto.DistributorByIdDTO;
import com.example.demo.api.distributor.model.Distributor;
import com.example.demo.api.distributor.repository.DistributorRepository;
import com.example.demo.api.ingredient.dto.CreateUpdateIngredientDTO;
import com.example.demo.api.ingredient.dto.IngredientByIdDTO;
import com.example.demo.api.ingredient.model.Ingredient;
import com.example.demo.api.product.dto.CreateUpdateProductDTO;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistributorService {

    private final DistributorRepository distributorRepository;
    private final ProductService productService;

    public void create(CreateUpdateDistributorDTO createUpdateDistributorDTO) {
        Product product = productService.getById(createUpdateDistributorDTO.getProductId());

        Distributor distributor = Distributor.builder()
                .name(createUpdateDistributorDTO.getName())
                .phone(createUpdateDistributorDTO.getPhone())
                .email(createUpdateDistributorDTO.getEmail())
                .product(product)
                .build();

        distributorRepository.save(distributor);
    }

    public Distributor getById(Long id) {
        return distributorRepository.findById(id).orElseThrow(() -> new RuntimeException("Distributor with id " + id + " could not be found!"));
    }

    public void update(Long id, CreateUpdateDistributorDTO createUpdateDistributorDTO) {
        Distributor distributor = getById(id);
        Product product = productService.getById(createUpdateDistributorDTO.getProductId());

        distributor.setName(createUpdateDistributorDTO.getName());
        distributor.setPhone(createUpdateDistributorDTO.getPhone());
        distributor.setEmail(createUpdateDistributorDTO.getEmail());
        distributor.setProduct(product);

        distributorRepository.save(distributor);
    }

    public PaginationResponseDTO<DistributorByIdDTO> getPage(PaginationParameters paginationParameters) {
        Page<Distributor> page = distributorRepository.findAll(PageRequest.of(paginationParameters.getPage(), paginationParameters.getSize()));

        List<DistributorByIdDTO> distributors = page.getContent().stream()
                .map(EntityToDTOMappingUtil::mapToDTO)
                .toList();

        PaginationInfoDTO paginationInfo = new PaginationInfoDTO(
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );

        return new PaginationResponseDTO<>(distributors, paginationInfo);
    }

    public void deleteById(Long id) {
        distributorRepository.deleteById(id);
    }
}
