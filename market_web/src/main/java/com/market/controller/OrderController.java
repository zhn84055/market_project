package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.domain.Order;
import com.market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAll.do")
//    @Secured("ROLE_ADMIN")//订单详情非ROLE_ADMIN权限也能访问
    public ModelAndView findAll(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Order> list = orderService.findAll(page, size);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    /**
     * 根据订单id查找订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        Order orderById = orderService.findById(id);
        modelAndView.addObject("orders", orderById);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
