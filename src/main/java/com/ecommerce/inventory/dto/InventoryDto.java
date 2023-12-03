package com.ecommerce.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InventoryDto {
    private UUID id;
    private String productName;
    private Integer availableQuantity;
    private String description;
    private Double price;
}
