package com.shuizhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shuizhi.dao.GatewayMapper;
import com.shuizhi.model.Gateway;
import com.shuizhi.service.GatewayService;
import com.shuizhi.util.HttpRequestUtil;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李鹏熠
 * @create 2019/7/9 10:43
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class GatewayServiceImpl implements GatewayService {
    @Autowired
    private GatewayMapper gatewayMapper;

    @Override
    public String addGateway(Gateway gateway) throws Exception {
        Map<String, String> map = new HashMap<>();
        if (gateway.getName().equals("") && gateway.getName() != null) {
            map.put("name", gateway.getName());
        }
        if (!(gateway.getRemarks().equals("")) && gateway.getRemarks() != null) {
            map.put("description", gateway.getRemarks());
        } else {
            map.put("description", "");
        }
        if (!(gateway.getDeveui().equals("")) && gateway.getDeveui() != null) {
            map.put("devEUI", gateway.getDeveui());
        }
        if (!(gateway.getClassc().equals("")) && gateway.getClassc() != null) {
            map.put("classc", gateway.getClassc());
        }
        if (gateway.getNetType().toLowerCase().equals("otaa")) {
            if (!(gateway.getAppkey().equals("")) && gateway.getAppkey() != null) {
                map.put("appKey", gateway.getAppkey());
            }
        } else {
            if(!(gateway.getAppskey().equals(""))&&gateway.getAppskey()!=null){
                map.put("appsKey",gateway.getAppskey());
            }
            if(!(gateway.getNwkskey().equals(""))&&gateway.getNwkskey()!=null){
                map.put("nwkSKey",gateway.getNwkskey());
            }
            if(!(gateway.getDevaddr().equals(""))&&gateway.getDevaddr()!=null){
                map.put("devAddr",gateway.getDevaddr());
            }
        }
        String result=null;
        try {
            if (gatewayMapper.add(gateway)>0){
                String url = "http://gd.v6plus.com/lora/node/";
                String s = HttpRequestUtil.post(url, map);
                result = JSONObject.parseObject(s).getString("info");
                if(!result.equals("操作成功！")){
                    throw new Exception(result);
                }
            }else{
                result="添加失败";
            }
        }catch (Exception e){
            e.printStackTrace();
            result="数据库添加失败";
        }

        return result;
    }

    @Override
    public int delete(String deveui) {
        String url="http://gd.v6plus.com/lora/node/"+deveui;
        String s= HttpRequestUtil.delete(url);
        int result=0;
        if(s.equals("")||s==null){
            result =gatewayMapper.deleteByDevEUI(deveui);
        }
        return result;
    }

    @Override
    public String xiafaGateway(Map<String,String> map) {
        String url = "http://gd.v6plus.com/lora/send/";
        String s = HttpRequestUtil.post(url, map);
        return s;
    }
}