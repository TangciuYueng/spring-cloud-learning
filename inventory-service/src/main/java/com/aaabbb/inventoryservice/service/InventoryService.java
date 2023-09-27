package com.aaabbb.inventoryservice.service;

import com.aaabbb.inventoryservice.dto.InventoryResponse;
import com.aaabbb.inventoryservice.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryMapper inventoryMapper;
//    @Transactional(readOnly = true)
//    public boolean isInStock(String skuCode) {
//        // 检查是否能find到Inventory类型
//        return inventoryMapper.findBySkuCode(skuCode).isPresent();
//    }
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryMapper.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }

}
