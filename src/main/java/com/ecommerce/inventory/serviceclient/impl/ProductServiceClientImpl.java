package com.ecommerce.inventory.serviceclient.impl;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.serviceclient.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceClientImpl implements ProductServiceClient {
    private final RestTemplate restTemplate;

    @Value("${productServiceBaseUrl}")
    private String productServiceBaseUrl;


    public void createProduct(ProductDto productDto) {
        String url = String.format("%s/products", productServiceBaseUrl);
        ParameterizedTypeReference<ProductDto> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<ProductDto> requestEntity = new HttpEntity<>(productDto);
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
        );

    }
}
