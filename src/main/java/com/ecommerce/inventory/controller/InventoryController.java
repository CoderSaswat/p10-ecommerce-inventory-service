package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping("/{inventoryId}")
    public InventoryDto getAvailableQuantity(@PathVariable UUID inventoryId) {
        return inventoryService.getInventoryProduct(inventoryId);
    }

    @PutMapping("/{productId}")
    public InventoryDto updateAvailableQuantity(
            @PathVariable UUID productId,
            @RequestBody InventoryDto inventoryDto
    ) {
        return inventoryService.updateInventoryProduct(productId, inventoryDto);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody InventoryDto inventoryDto) {
        return inventoryService.createInventoryProduct(inventoryDto);
    }

    @GetMapping
    public List<InventoryDto> getAllInventoryProducts() {
        return inventoryService.getAllInventoryProducts();
    }

    @DeleteMapping("/{inventoryId}")
    public void deleteInventoryProduct(@PathVariable UUID inventoryId) {
        inventoryService.deleteInventoryProduct(inventoryId);
    }


}
