package com.shuizhi.config;


import com.shuizhi.model.WaterQuality;
import com.shuizhi.service.WaterQualityService;

import com.shuizhi.service.impl.WaterQualityServiceImpl;
import com.shuizhi.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;



public class ServerThread implements Runnable{
    private Socket client = null;
    //private static Logger logger = Logger.getLogger(ServerThread.class);
    private final static Logger logger = LoggerFactory.getLogger(ServerThread.class);
    private WaterQualityService waterQualityService= SpringUtil.getBean(WaterQualityServiceImpl.class);

    public ServerThread(Socket client) {
        this.client = client;
    }

    // 处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public  void execute(Socket client) {
        try {
            // 获取Socket的输出流，用来向客户端发送数据
            OutputStream out = client.getOutputStream();
            InputStream ins = client.getInputStream();
            String s;
            ByteArrayOutputStream bos = null;
            boolean flag = true;
            int len = 0;
            byte insbuff[] = new byte[1024];
            int crc=0;
            len = ins.read(insbuff);
            if (len == -1) {
                flag = false;
            }
            while (flag) {
                String str = null;
                String jiexi=null;
                bos = new ByteArrayOutputStream();
                len = ins.read(insbuff);
                bos.write(insbuff, 0, len);
                str = TLVTools.bytesToHexString(insbuff,len);
                jiexi=TLVTools.tlvA(str);
                System.out.println("水质解析:"+jiexi);
                logger.info("crc:" + crc);
                logger.info("水质数据:" + str);
                logger.info("水质解析:"+jiexi);



                /*                System.out.println("来自客户端:" + str);*/
                s = "回复成功\r\n";
                out.write(s.getBytes("GBK"));
                out.flush();
                if(!jiexi.equals("长度不足")){
                    add(jiexi,str);
                }
                /*                System.out.println(s);*/
                logger.info(s);
            }
            out.close();
            client.close();
        } catch (Exception e) {
            /*            System.out.println("用户：" + client.getInetAddress().toString() + "断开连接!");*/
            logger.info("用户：" + client.getInetAddress().toString() + "断开连接!");
        }
    }

    public void run() {
        execute(client);
    }

    public  void add(String jiexi,String str){
        String [] arr=jiexi.split(" ");
        WaterQuality waterQuality=new WaterQuality();
        waterQuality.setTemp(arr[5]);
        waterQuality.setConductive(arr[6]);
        waterQuality.setPh(arr[7]);
        waterQuality.setDoConcentration(arr[8]);
        waterQuality.setTurbidity(arr[9]);
        waterQuality.setVoltage(arr[10]);
        waterQuality.setNodeid(arr[2]);
        waterQuality.setDownlinkdata(str);
        waterQuality.setDecodedata(jiexi);
        waterQuality.setCreatedtime(new Date());
    try {
        waterQualityService.add(waterQuality);
    }catch (Exception e){
        e.printStackTrace();
    }

    }

}
