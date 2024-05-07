package com.example.demo.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO<T> {
    private List<T> items;
    private PaginationInfoDTO paginationInfo;
}
