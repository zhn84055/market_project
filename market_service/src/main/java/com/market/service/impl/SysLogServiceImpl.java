package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.market.dao.SysLogDao;
import com.market.domain.SysLog;
import com.market.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        //PageHelper分页
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }
}
