package com.shuizhi.service.impl;

import com.shuizhi.dao.NodeMapper;
import com.shuizhi.model.Node;
import com.shuizhi.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李鹏熠
 * @create 2019/6/18 16:18
 */
@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeMapper nodeMapper;


    @Override
    public Node getNode(String nodeId){
        return nodeMapper.getByNodeId(nodeId);
    }
}