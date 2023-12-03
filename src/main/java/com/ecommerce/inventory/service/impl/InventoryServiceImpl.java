package com.ecommerce.inventory.service.impl;

import com.ecommerce.inventory.config.mapper.PatchMapper;
import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.enums.ResponseCodes;
import com.ecommerce.inventory.exception.ENFException;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import com.ecommerce.inventory.service.InventoryService;
import com.ecommerce.inventory.serviceclient.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final PatchMapper patchMapper;
    private final ProductServiceClient productServiceClient;
    @Override
    public InventoryDto getInventoryProduct(UUID inventoryId) {
        Inventory inventory = getInventoryById(inventoryId);
        return mapToInventoryDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryProducts() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream()
                .map(this::mapToInventoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInventoryProduct(UUID inventoryId) {
        Inventory inventory = getInventoryById(inventoryId);
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto updateInventoryProduct(UUID productId, InventoryDto inventoryDto) {
        Inventory inventory = getInventoryById(productId);
        patchMapper.map(inventoryDto, inventory);
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return mapToInventoryDto(updatedInventory);
    }

    @Override
    public ProductDto createInventoryProduct(InventoryDto inventoryDto) {
        Inventory newInventory = mapToInventoryEntity(inventoryDto);
        newInventory.setId(UUID.randomUUID());
        Inventory savedInventory = inventoryRepository.save(newInventory);
        ProductDto productDto = generateProductDto(inventoryDto, savedInventory);
        productServiceClient.createProduct(productDto);
        return productDto;
    }

    private ProductDto generateProductDto(InventoryDto inventoryDto, Inventory savedInventory) {
        ProductDto productDto = modelMapper.map(inventoryDto, ProductDto.class);
        productDto.setName(inventoryDto.getProductName());
        productDto.setInventoryId(savedInventory.getId());
        return productDto;
    }

    // Helper methods for mapping and validation

    private Inventory getInventoryById(UUID inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ENFException(Inventory.class,"id",inventoryId, ResponseCodes.ENTITY_NOT_FOUND));
    }

    private InventoryDto mapToInventoryDto(Inventory inventory) {
        return modelMapper.map(inventory, InventoryDto.class);
    }

    private Inventory mapToInventoryEntity(InventoryDto inventoryDto) {
        return modelMapper.map(inventoryDto, Inventory.class);
    }
}
