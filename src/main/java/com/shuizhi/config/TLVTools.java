package com.shuizhi.config;

/**
 * @author
 * @create 2019/5/28 11:00
 */
public class TLVTools {

    public static String tlvA(String str) {
        String asd ="";
        String output = str.substring(0, 2);
        int decimal = Integer.parseInt(output, 16);
        char tag = (char) decimal;
        if(tag=='Q'||tag=='A'){
            asd += tag;
            output = str.substring(2, 4);
            int len = Integer.parseInt(output, 16);
            asd += " " + len + " ";
            for (int i = 2; i < 2 + len; i++) {
                asd += (char) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            if (str.length() > (len + 2) * 2) {
                output = str.substring((len + 2) * 2, (len + 3) * 2);
                char cc = (char) Integer.parseInt(output, 16);
                String tag2 = String.valueOf(cc);
                if (tag2.equals("#")) {
                    asd += " " + tag2 + " ";
                    int num = Integer.parseInt(str.substring((len + 3) * 2, (len + 4) * 2), 16);

                    asd += +num + " ";
                    output = str.substring((len + 4) * 2);
                    if(output.length()-4==num*2){
                        for (int i = 0; i < num / 2; i++) {
                            String asdada = output.substring(i * 4, i * 4 + 4);
                            asd += Integer.parseInt(asdada, 16) + " ";
                        }
                    }else{
                        asd="长度不足";
                    }
                }
            }
        }
        return  asd;
    }

    public static String bytesToHexString(byte[] src,int len){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}