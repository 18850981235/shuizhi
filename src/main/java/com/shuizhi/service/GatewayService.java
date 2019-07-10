package com.shuizhi.service;

import com.shuizhi.model.Gateway;

import java.util.Map;

/**
 * @author 李鹏熠
 * @create 2019/7/9 10:47
 */
public interface GatewayService {
    String addGateway(Gateway gateway)throws Exception;
    int delete(String deveui);
    String xiafaGateway(Map<String,String> map);
}
