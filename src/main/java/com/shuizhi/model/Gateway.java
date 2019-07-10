package com.shuizhi.model;

import lombok.Data;

import java.util.Date;

@Data
public class Gateway {
    private Integer id;

    private String name;

    private String deveui;

    private String remarks;

    private String classc;

    private String appkey;

    private String appskey;

    private String nwkskey;

    private String devaddr;

    private String manufacturer;

    private String apply;

    private String appeui;

    private String model;

    private String type;

    private String reportFrequency;

    private String netType;

    private Date netTime;

    private String site;

    private String longitude;

    private String latitude;

    }