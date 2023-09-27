package com.aaabbb.productservice.mapper;

import com.aaabbb.productservice.dto.ProductResponse;
import com.aaabbb.productservice.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("""
            insert into product(name, description, price)
            values(#{name}, #{description}, #{price})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    // ALTER TABLE product MODIFY COLUMN id INT AUTO_INCREMENT;
    // 需要在数据库中设置如上语句
    void add(Product product);

    @Select("""
            select id, name, description, price
            from product
            """)
    List<Product> findAll();
}
