package com.shuizhi.controller;


import com.shuizhi.model.Gateway;
import com.shuizhi.service.GatewayService;

import com.shuizhi.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李鹏熠
 * @create 2019/7/9 9:07
 */
@Controller
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public String addGateway(Gateway gateway) throws Exception{
        gatewayService.addGateway(gateway);
        return null;
    }
    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public String deleteGateway(@RequestParam("deveui") String deveui) {
        gatewayService.delete(deveui);
        return null;
    }

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public String xiafaGateway(@RequestParam("data") String data,
                               @RequestParam("port") String port,
                               @RequestParam("deveui") String deveui,
                               @RequestParam("confirmed")String confirmed) {
        Map<String,String> map=new HashMap<>();
        map.put("data", Base64Util.encryption(data));
        map.put("port",port);
        map.put("deveui",deveui);
        map.put("confirmed",confirmed);
        gatewayService.xiafaGateway(map);
        return null;
    }
}