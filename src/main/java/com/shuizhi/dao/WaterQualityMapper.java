package com.shuizhi.dao;


import com.shuizhi.model.WaterQuality;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface WaterQualityMapper {
    int add(WaterQuality waterQuality);
    List<WaterQuality> list();
    List<WaterQuality> getByNodeId(@Param("nodeid")String nodeid);
}