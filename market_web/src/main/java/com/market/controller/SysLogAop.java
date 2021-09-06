package com.market.controller;

import com.market.domain.SysLog;
import com.market.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Description:heima_ssm
 * Created by admin  on 2020/3/6  15:47
 */

@Component
@Aspect
public class SysLogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;


    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    private String url;//访问路径
    private String ip;//访问的ip地址
    private String username;//访问用户名


    //前置通知,主要是获取开始时间，执行的类是哪一个，访问的是哪一个方法
    @Before("execution(* com.market.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法名称
        Object[] args = jp.getArgs();//获取访问的方法的参数
        //获取具体执行方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }

    }

    //后置通知
    @After("execution(* com.market.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//获取了访问的时长
        //获取url
        if (clazz != null && method != null && clazz != SysLogAop.class) {
            //1,获取类上的@RequestMapping的值
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] classValue = clazzAnnotation.value();

                //2,获取方法上的@RequestMapping的值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];


                    //获取访问的ip地址
                    ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获得了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);//执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名:]" + clazz.getName() + "[方法名：]" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用service完成记录操作
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
