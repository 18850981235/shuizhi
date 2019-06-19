package com.shuizhi.model;

import lombok.Data;

import java.util.Date;
@Data
public class Node {
    private Integer id;

    private String nodeid;

    private Date createdtime;

    private String status;

    private String gathersite;

    private String stack;

    private String nodeEquipment;
}
