package com.example.demo.util.pagination;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationParameters {
    private int page = 0;
    private int size = 10;
}
