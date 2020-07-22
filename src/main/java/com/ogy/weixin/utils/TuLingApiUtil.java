package com.ogy.weixin.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.List;

// 图灵聊天机器人
public class TuLingApiUtil {

    //public static final String apikey = "a4016a58b56a4a67b956df5bbe1b47b9";
    public static final String APIKEY = "3660f1b9c5b148f588a2a31838db9bc2";

    //public static final String userId = "646505";
    public static final String USERID = "311630";

    public static final String URL = "http://openapi.tuling123.com/openapi/api/v2";

    public static final String CHARSET = "UTF-8";

    // 获取响应，得到响应的json字符串
    public static String getResult(String content){
        String result = "";
        CloseableHttpClient client=null;
        try {
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 组织数据,json格式
            StringEntity se = new StringEntity(getReqMes(content));
            // 设置编码格式
            se.setContentEncoding(CHARSET);
            // 设置数据类型
            se.setContentType("application/json");
            // 对于POST请求,把请求体填充进HttpPost实体.
            httpPost.setEntity(se);
            client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            System.out.println("code:"+code);
            if(code==200){
                // 通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                System.out.println("图灵聊天机器人 返回的结果: "+result);
            }else {
                System.out.println("请求失败; code="+code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 生成string(json)
    public static String getReqMes(String msg){
        // 请求json，里面包含reqType，perception，userInfo
        JSONObject reqJson = new JSONObject();

        // 输入类型:0-文本(默认)、1-图片、2-音频
        int reqType = 0;
        reqJson.put("reqType",reqType);

        // 输入信息,里面包含inputText，inputImage，selfInfo
        JSONObject perception = new JSONObject();
        // 输入的文本信息
        JSONObject inputText = new JSONObject();
        inputText.put("text",msg);
        perception.put("inputText",inputText);
        reqJson.put("perception",perception);

//        // 输入的图片信息
//        JSONObject inputImage = new JSONObject();
//        inputImage.put("url","");
//        perception.put("inputImage",inputImage);
//        // 个人信息，里面包含location
//        JSONObject selfInfo = new JSONObject();
//        // 包含city，province，street
//        JSONObject location = new JSONObject();
//        location.put("city","");
//        location.put("province","");
//        location.put("street","");
//        selfInfo.put("location",location);
//        perception.put("selfInfo",selfInfo);

        // 用户信息
        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey",APIKEY);
        userInfo.put("userId",USERID);
        reqJson.put("userInfo",userInfo);

        return reqJson.toString();
    }

    // 取出机器人回到的信息,string to jsonObject
    public static String getResultMes(String tulinPostStr){
        JSONObject jsonObject = JSONObject.fromObject(tulinPostStr);
        List<Object> results = (List<Object>) jsonObject.get("results");
        JSONObject resultObj = JSONObject.fromObject(results.get(0));
        JSONObject values = JSONObject.fromObject(resultObj.get("values"));
        return values.get("text").toString();
    }
    
}
