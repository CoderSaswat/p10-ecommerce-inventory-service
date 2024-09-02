package com.ecommerce.inventory.service;


import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.dto.ProductAvailabilityDto;
import com.ecommerce.inventory.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryDto getInventoryProduct(UUID inventoryId);
    InventoryDto updateInventoryProduct(UUID productId, InventoryDto inventoryDto);
    ProductDto createInventoryProduct(InventoryDto inventoryDto);
    void deleteInventoryProduct(UUID inventoryId);
    List<InventoryDto> getAllInventoryProducts();
    void updateProductAvailability(ProductAvailabilityDto productAvailabilityDto);
}
