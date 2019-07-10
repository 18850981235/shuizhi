package com.shuizhi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: Sim
 * Date: 2019-04-26
 * Description: MD5加密工具类
 */
public class MD5Util {

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = null;
        try {
            byteArray = inStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = md5Bytes[i] & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String MD5(String inStr) {
        String secret = "Abc%$G&!DES/!ECB/PKCS5Padding!128G";
        String raw = inStr + secret;
        String hexValue = MD5Util.string2MD5(raw).toUpperCase();
        return hexValue;
    }


}
