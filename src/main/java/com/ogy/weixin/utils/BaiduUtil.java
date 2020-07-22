package com.ogy.weixin.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "21504602";
    public static final String API_KEY = "yshuuSHWk5etm4ATBve01iEs";
    public static final String SECRET_KEY = "30r6o9uH6maxkGHm0BtntU026kpxumuX";

    public static void main(String[] args) {


    }

    public void img(){
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "D:\\WP\\idea\\weixin\\src\\main\\resources\\static\\img\\a.png";
//        JSONObject res = client.objectDetect(path, new HashMap<String, String>());
        JSONObject res = client.advancedGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
    }

    public static String baiduAi(String imgUrl){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = imgUrl;
//        String path = "D:\\WP\\idea\\weixin\\src\\main\\resources\\static\\img\\a.png";
        JSONObject res = client.generalUrl(path,new HashMap<String, String>());
//        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
        return res.toString(2);
    }

}
