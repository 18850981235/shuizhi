package com.shuizhi.util;

import java.util.Base64;

/**
 * @author 李鹏熠
 * @create 2019/7/4 17:30
 */
public class Base64Util {

    //加密成base64
    public static String encryption(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] data = encoder.encode(str.getBytes());
        return new String(data);
    }
    //解密base64
    public static String decode(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(str);
        return  new String(bytes);
    }

}