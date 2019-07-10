package com.shuizhi.dao;


import com.shuizhi.model.Gateway;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GatewayMapper {
    int deleteByDevEUI(String deveui);
    int add(Gateway gateway);
}