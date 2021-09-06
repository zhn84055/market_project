package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.domain.SysLog;
import com.market.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Description:日志
 * Created by admin  on 2020/3/6  20:32
 */

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll(page, size);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
