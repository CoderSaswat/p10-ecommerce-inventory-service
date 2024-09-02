package com.ecommerce.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductAvailabilityDto {
    private String productName;
    private Integer amountSold;
}
