package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.market.dao.OrderDao;
import com.market.domain.Order;
import com.market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 查找所有订单信息
     *
     * @return
     */
    @Override
    public List<Order> findAll(Integer page, Integer size) {
        //分页，必须在查询方法上，中间不能有其他语句
        PageHelper.startPage(page, size);
        return orderDao.findAll();
    }

    /**
     * 根据订单id查找订单信息
     *
     * @param id
     * @return
     */
    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }
}
