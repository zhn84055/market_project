package com.market.service;

import com.market.domain.Product;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有产品
     *
     * @return
     */
    List<Product> findAll(Integer page, Integer size);

    /**
     * 新增产品
     *
     * @param product
     */
    void save(Product product);

}
