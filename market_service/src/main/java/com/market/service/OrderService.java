package com.market.service;

import com.market.domain.Order;

import java.util.List;

public interface OrderService {
    /**
     * 查找所有订单信息
     *
     * @return
     */
    List<Order> findAll(Integer page, Integer size);

    /**
     * 根据订单id查找订单信息
     *
     * @param id
     * @return
     */
    Order findById(String id);
}
