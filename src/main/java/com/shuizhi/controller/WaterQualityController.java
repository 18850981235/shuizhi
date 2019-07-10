package com.shuizhi.controller;

import com.alibaba.fastjson.JSON;
import com.shuizhi.service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李鹏熠
 * @create 2019/6/13 9:32
 */
@Controller
@RequestMapping("waterQuality")
public class WaterQualityController {
    @Autowired
    private WaterQualityService waterQualityService;


    @RequestMapping()
    @ResponseBody
    public String getList() {
        return JSON.toJSONString(waterQualityService.getList());
    }
}