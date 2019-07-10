package com.shuizhi.service.impl;


import com.shuizhi.dao.WaterQualityMapper;
import com.shuizhi.model.WaterQuality;
import com.shuizhi.service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WaterQualityServiceImpl implements WaterQualityService {

    @Autowired
    private WaterQualityMapper waterQualityMapper;


    @Override
    public int add(WaterQuality waterQuality) {
        return waterQualityMapper.add(waterQuality);
    }

    @Override
    public List<WaterQuality> getList() {
        return waterQualityMapper.list();
    }

    @Override
    public List<WaterQuality> getNodeId(String nodeId) {
        return waterQualityMapper.getByNodeId(nodeId);
    }


    public List<WaterQuality> getId(int id) {
        if (id != 0) {
            switch (id) {
                case 1:
                    System.out.println("id为1");
                    break;
                case 2:
                    System.out.println("id为2");
                case 3:
                    System.out.println("进入2  也进入3");
                    break;
                case 4:
                    waterQualityMapper.list();
                    break;
                case 5:
                    waterQualityMapper.getByNodeId("");
                    break;
                default:

                    break;
            }
        }

        return null;
    }
}
