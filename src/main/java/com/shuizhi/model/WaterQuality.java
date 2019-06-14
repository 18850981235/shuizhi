package com.shuizhi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class WaterQuality {
    private Integer id;
    //温度
    private String temp;
    //导电率
    private String conductive;
    //pH
    private String ph;
    //溶解氧浓度
    private String doConcentration;
    //浊度
    private String turbidity;
    //电压
    private String voltage;
    //节点ID
    private String nodeid;
    //下行数据
    private String downlinkdata;
    //解码数据
    private String decodedata;
    //添加时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdtime;
}