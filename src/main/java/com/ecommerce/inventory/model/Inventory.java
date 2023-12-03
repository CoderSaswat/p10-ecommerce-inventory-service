package com.ecommerce.inventory.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document("inventory")
public class Inventory {
    @Id
    private UUID id;
    private String productName;
    private Integer availableQuantity;
}
