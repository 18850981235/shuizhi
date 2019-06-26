package com.shuizhi.config;


import com.shuizhi.model.Node;
import com.shuizhi.model.WaterQuality;
import com.shuizhi.service.NodeService;
import com.shuizhi.service.WaterQualityService;

import com.shuizhi.service.impl.NodeServiceImpl;
import com.shuizhi.service.impl.WaterQualityServiceImpl;
import com.shuizhi.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;


public class ServerThread implements Runnable {
    private Socket client = null;
    private final static Logger logger = LoggerFactory.getLogger(ServerThread.class);
    private WaterQualityService waterQualityService = SpringUtil.getBean(WaterQualityServiceImpl.class);
    private NodeService nodeService=SpringUtil.getBean(NodeServiceImpl.class);

    public ServerThread(Socket client) {
        this.client = client;
    }

    // 处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public void execute(Socket client) {
        try {
            // 获取Socket的输出流，用来向客户端发送数据
            OutputStream out = client.getOutputStream();
            InputStream ins = client.getInputStream();
            String s;
            ByteArrayOutputStream bos = null;
            boolean flag = true;
            int len = 0;
            byte insbuff[] = new byte[1024];
            len = ins.read(insbuff);
            if (len == -1) {
                flag = false;
            }
            while (flag) {
                String str = null;
                String jiexi = null;
                bos = new ByteArrayOutputStream();
                len = ins.read(insbuff);
                bos.write(insbuff, 0, len);
                str = TLVTools.bytesToHexString(insbuff, len);
                jiexi = TLVTools.tlvA(str);

                //logger.info("crc:" + crc);
                logger.info("水质数据:" + str);
                logger.info("水质解析:" + jiexi);

                out.flush();
                if (!jiexi.equals("长度不足")) {
                    add(jiexi, str);
                    s = "4101002044";
                    out.write(s.getBytes("GBK"));
                    logger.info(s);
                }

            }
            out.close();
            client.close();
        } catch (Exception e) {
            logger.info("用户：" + client.getInetAddress().toString() + "断开连接!");
        }
    }

    public void run() {
        execute(client);
    }

    public void add(String jiexi, String str) {
        WaterQuality waterQuality=null;
        String[] arr = jiexi.split(" ");
        Node node=nodeService.getNode(arr[2]);
        String[] equipments=node.getNodeEquipment().split(",");
        String[] data=new String[equipments.length];
        for(int i=0;i<equipments.length;i++){
                data[i]=arr[i+5];
        }
        if(node!=null){
            waterQuality = new WaterQuality();
            for (int i=0;i<equipments.length;i++) {
                if(equipments[i].equals("ph")){
                    waterQuality.setPh(data[i]);
                }else if(equipments[i].equals("电压")){
                    waterQuality.setVoltage(data[i]);
                }else if(equipments[i].equals("温度")){
                    waterQuality.setTemp(data[i]);
                }else if(equipments[i].equals("电导率")){
                    waterQuality.setConductive(data[i]);
                }else if(equipments[i].equals("溶解氧浓度")){
                    waterQuality.setDoConcentration(data[i]);
                }else if(equipments[i].equals("浊度")){
                    waterQuality.setTurbidity(data[i]);
                }
            }
            waterQuality.setNodeid(arr[2]);
            waterQuality.setDownlinkdata(str);
            waterQuality.setDecodedata(jiexi);
            waterQuality.setCreatedtime(new Date());
        }
        try {
            if(waterQuality!=null){
                waterQualityService.add(waterQuality);
                logger.info("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
