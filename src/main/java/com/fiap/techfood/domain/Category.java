package com.fiap.techfood.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {

    private UUID id;
    private String name;

}
