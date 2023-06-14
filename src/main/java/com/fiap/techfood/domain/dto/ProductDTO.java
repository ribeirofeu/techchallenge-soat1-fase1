package com.fiap.techfood.domain.dto;

import com.fiap.techfood.domain.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String name;
    private BigDecimal unitValue;
    private Category category;
    private String description;

}
