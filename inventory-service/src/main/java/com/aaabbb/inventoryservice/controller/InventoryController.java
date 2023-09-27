package com.aaabbb.inventoryservice.controller;

import com.aaabbb.inventoryservice.dto.InventoryResponse;
import com.aaabbb.inventoryservice.service.InventoryService;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    // 可以将参数写在url当中
//    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//        return inventoryService.isInStock(skuCode);
//    }

    // 想一次传入多个参数
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
