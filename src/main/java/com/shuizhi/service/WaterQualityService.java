package com.shuizhi.service;


import com.shuizhi.model.WaterQuality;

import java.util.List;


public interface WaterQualityService {
    int add(WaterQuality waterQuality);
    List<WaterQuality> getList();
    List<WaterQuality> getNodeId(String nodeId);
}
