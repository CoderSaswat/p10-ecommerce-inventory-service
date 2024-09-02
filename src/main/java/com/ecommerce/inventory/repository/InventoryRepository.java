package com.ecommerce.inventory.repository;

import com.ecommerce.inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, UUID> {
    Inventory findByProductName(String productName);
}
