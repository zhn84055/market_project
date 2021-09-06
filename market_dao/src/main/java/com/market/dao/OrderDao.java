package com.market.dao;

import com.market.domain.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {
    /**
     * 查找所有订单信息
     *
     * @return
     */
    List<Order> findAll();

    /**
     * 根据订单id查找订单信息
     *
     * @param id
     * @return
     */
    Order findById(String id);
}
