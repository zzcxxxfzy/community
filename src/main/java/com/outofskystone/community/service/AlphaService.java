package com.outofskystone.community.service;


import com.outofskystone.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {

    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;//注入bean

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct//在实例化之后调用
    public void init() {
        System.out.println("初始化alphaservice");
    }

    @PreDestroy//在程序结束之前销毁
    public void destroy() {
        System.out.println("销毁alphaservice");
    }

    public String find() {
        return alphaDao.select();
    }
}
