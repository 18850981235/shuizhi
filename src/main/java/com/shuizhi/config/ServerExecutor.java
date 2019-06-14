package com.shuizhi.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class ServerExecutor  implements ApplicationRunner {
    //private  static  Logger logger = Logger.getLogger(ServerExecutor.class);
    private final static Logger logger = LoggerFactory.getLogger(ServerExecutor.class);
    public  void jieshou() throws IOException{
        int port=8111;
        ServerSocket server = new ServerSocket(port);
        String port_open= "服务器已开启，端口："+port+" 已打开，等待连接......";
        //System.out.println(port_open);
        logger.info(port_open);
        Socket client = null;
        Executor service = Executors.newCachedThreadPool();
        boolean f = true;
        String s=null;
        while(f){
            client = server.accept();
            OutputStream out = client.getOutputStream();
            String ip=client.getInetAddress().toString();
            s="用户："+ip+" 连接成功！\r\n";
            //System.out.println(s);
            out.write(s.getBytes("GBK"));
            logger.info(s);

            service.execute(new ServerThread(client));
        }
        server.close();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jieshou();
    }
}
