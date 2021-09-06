package com.market.service;

import com.market.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer size) throws Exception;
}
