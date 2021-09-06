package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.domain.Product;
import com.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询所有产品
     *
     * @return
     */
    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")//订单详情非ROLE_ADMIN权限也能访问
    public ModelAndView findAll(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Product> list = productService.findAll(page, size);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 新增产品
     *
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll.do";//重定向，查询数据
    }
}
