package com.ecommerce.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer availableQuantity;
    private UUID inventoryId;
}
