package com.ogy.weixin.service.impl;

import com.ogy.weixin.pojo.*;
import com.ogy.weixin.service.WeixinCoreService;
import com.ogy.weixin.utils.BaiduUtil;
import com.ogy.weixin.utils.TuLingApiUtil;
import com.ogy.weixin.utils.WeixinUtil;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Service
public class WeixinCoreServiceImpl implements WeixinCoreService {

    public static final String APPID = "wx578d6047e444a0cd";

    public static final String APPSECRET = "ab00d5a9654950617f4042784447ebe5";

    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static AccessToken ACCESS_TOKEN;

//    @Override
//    public String weixinMessageHandelCoreService() {
//        return null;
//    }

    @Override
    public String getRespose(Map<String, String> parseXml) {
        BaseMessage baseMessage = null;
        String msgType = parseXml.get("MsgType");
        switch (msgType){
            case "text":
                baseMessage=dealTextMessage(parseXml);
                System.out.println("消息对象： "+baseMessage);
                break;
            case "image":
                baseMessage=dealImage(parseXml);
                break;
            case "voice":

                break;
            case "video":

                break;
            case "shortvideo":

                break;
            case "location":

                break;
            case "link":

                break;
            case "event":
                System.out.println("event");
                baseMessage = dealEvent(parseXml);
                break;
        }
        String xml = null;
        if(baseMessage!=null){
            xml = pojoToXml(baseMessage);
        }
        return xml;
    }

    private BaseMessage dealImage(Map<String, String> parseXml) {
        System.out.println("dealImage()...");
        String imgUrl = parseXml.get("PicUrl");
        String json = BaiduUtil.baiduAi(imgUrl);
        System.out.println(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        Iterator<JSONObject> iterator = jsonArray.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while(iterator.hasNext()){
            JSONObject jsonObject1 = iterator.next();
            stringBuilder.append(jsonObject1.getString("words")).append("\r\n");

        }
        return new TextMessage(parseXml,stringBuilder.toString());

    }

    private BaseMessage dealEvent(Map<String, String> parseXml) {
        String event = parseXml.get("Event");
        switch (event){
            case "CLICK":
                return dealClick(parseXml);
            case "VIEW":
                return dealView(parseXml);
            default:
                return null;
        }
    }

    private BaseMessage dealView(Map<String, String> parseXml) {
        return null;
    }

    private BaseMessage dealClick(Map<String, String> parseXml) {
        String eventKey = parseXml.get("EventKey");
        switch (eventKey){
            case "1-1":
                return new TextMessage(parseXml,"你点击了一级菜单");
            default:
                return null;
        }
    }

    public BaseMessage dealTextMessage(Map<String, String> parseXml) {
        String msg = parseXml.get("Content");

        if(msg.equals("图文")){
            ArrayList<Article> articles = new ArrayList<>();
            articles.add(new Article("图文消息的标题","图文消息的描述","http://mmbiz.qpic.cn/mmbiz_jpg/YNClkJrd8GhV8JjB7lr9Cer1ZLS84rIAbic0B1nKicWTHx90zBZS4IuwMaGOcNUj8TrnGIXlbQqnfDaSPdsyllFg/0","http://www.baidu.com"));
            NewsMessage newsMessage = new NewsMessage(parseXml, articles);
            return newsMessage;
        }

        String resp = TuLingApiUtil.getResult(msg);
        String respMsg = TuLingApiUtil.getResultMes(resp);
        TextMessage textMessage = new TextMessage(parseXml,respMsg);
        return textMessage;
    }

    // 将对象转为string(xml)
    public static String pojoToXml(BaseMessage baseMessage){
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(MusicMessage.class);
        xStream.processAnnotations(NewsMessage.class);
        xStream.processAnnotations(VideoMessage.class);
        xStream.processAnnotations(VoiceMessage.class);
        xStream.processAnnotations(Article.class);
        xStream.processAnnotations(Music.class);
        String xml = xStream.toXML(baseMessage);
        return xml;
    }

    private static void getToken(){
        String url = GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String tokenStr = WeixinUtil.get(url);
        System.out.println(tokenStr);
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        String accessToken = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        ACCESS_TOKEN = new AccessToken(accessToken,expiresIn);
    }

    public static String getAccessToken(){
        if(ACCESS_TOKEN==null||ACCESS_TOKEN.isExpired()){
            getToken();
        }
        return ACCESS_TOKEN.getAccessToken();
    }
}
