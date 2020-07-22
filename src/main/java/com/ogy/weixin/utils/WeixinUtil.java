package com.ogy.weixin.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

//        参数	描述
//        signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//        timestamp	时间戳
//        nonce	随机数
//        echostr	随机字符串

//    1）将token、timestamp、nonce三个参数进行字典序排序
//    2）将三个参数字符串拼接成一个字符串进行sha1加密
//    3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

public class WeixinUtil {

    // 与接口配置信息中的 Token 要一致
    public static final String TOKEN = "ogy";

    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr = {TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        String str = arr[0]+arr[1]+arr[2];
        String temStr = sha1(str);
        return temStr.equals(signature);
    }

    private static String sha1(String str) {
        if(str==null||str.length()==0){
            return null;
        }
        char[] digit = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] bytes = md.digest(str.getBytes());
            for(byte b:bytes){
                sb.append(digit[(b>>4)&15]);
                sb.append(digit[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String get(String str){
        try {
            URL url = new URL(str);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            StringBuilder builder = new StringBuilder();
            while((len=is.read(b))!=-1){
                builder.append(new String(b,0,len));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String str,String data){
        try {
            URL url = new URL(str);
            URLConnection connection = url.openConnection();
            // 设置为数据的可发送状态
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.close();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            StringBuilder builder = new StringBuilder();
            while((len=is.read(b))!=-1){
                builder.append(new String(b,0,len));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
