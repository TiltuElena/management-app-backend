package com.example.demo.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInfoDTO {
    private Long total;
    private Integer currentPage;
    private Integer itemsPerPage;
    private Integer lastPage;
}
