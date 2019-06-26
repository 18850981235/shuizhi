package com.shuizhi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 李鹏熠
 * @create 2019/6/24 15:06
 */
@Component
public class ServerIssued {

    private final static Logger logger = LoggerFactory.getLogger(ServerIssued.class);

    public void xiafa(String str){
        int port = 8111;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();
            OutputStream out = client.getOutputStream();
            out.write(str.getBytes("GBK"));
            logger.info(str);
        }catch (IOException e){
            e.printStackTrace();
        }

    }




}
