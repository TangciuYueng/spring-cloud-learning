package com.aaabbb.productservice.service;

import com.aaabbb.productservice.dto.ProductRequest;
import com.aaabbb.productservice.dto.ProductResponse;
import com.aaabbb.productservice.mapper.ProductMapper;
import com.aaabbb.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productMapper.add(product);
        log.info("Product {} is added", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products =  productMapper.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
