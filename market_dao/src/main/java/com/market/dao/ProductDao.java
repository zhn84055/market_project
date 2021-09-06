package com.market.dao;

import com.market.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有产品
     *
     * @return
     */
    List<Product> findAll();

    /**
     * 新增产品
     *
     * @param product
     */
    void save(Product product);

    /**
     * 根据id查找商品
     *
     * @return
     */
    Product findById(String id);
}
