package com.shuizhi.service.impl;

import com.shuizhi.config.ServerIssued;
import com.shuizhi.service.IssuedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李鹏熠
 * @create 2019/6/26 17:44
 */

@Service
public class IssuedServiceImpl implements IssuedService {

    @Autowired
    private ServerIssued serverIssued;

    @Override
    public void issued(String str)  {
            serverIssued.xiafa(str);
    }
}