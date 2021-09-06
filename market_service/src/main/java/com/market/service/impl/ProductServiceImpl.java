package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.market.dao.ProductDao;
import com.market.domain.Product;
import com.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAll(Integer page, Integer size) {
        //分页，必须在查询方法上，中间不能有其他语句
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    /**
     * 新增产品
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

}
