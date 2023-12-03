package com.ecommerce.inventory.serviceclient;

import com.ecommerce.inventory.dto.ProductDto;

public interface ProductServiceClient {
    void createProduct(ProductDto productDto);
}
