package com.shuizhi;

import com.shuizhi.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringUtil.class)
public class ShuizhiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShuizhiApplication.class, args);
    }

}
