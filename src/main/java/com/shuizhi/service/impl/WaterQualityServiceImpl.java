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
}
