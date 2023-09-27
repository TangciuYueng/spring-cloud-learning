package com.aaabbb.inventoryservice.mapper;

import com.aaabbb.inventoryservice.model.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InventoryMapper {
    @Select("SELECT * FROM inventory WHERE sku_code = #{skuCode}")
    Optional<Inventory> findBySkuCode(String skuCode);

    @Select("""
            select * from inventory
            where sku_code in (#{skuCode})
            """)
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
