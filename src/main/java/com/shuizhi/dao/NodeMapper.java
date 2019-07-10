package com.shuizhi.dao;


import com.shuizhi.model.Node;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NodeMapper {
    int add(Node node);
    Node getByNodeId(String nodeId);
    Node getByNodeId1(String nodeId);
    List<Node> getList();
}