package com.fiap.techfood.domain.products;

import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private String description;

    public static Product fromProductDTO(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }

}
