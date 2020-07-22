package com.ogy.weixin.test;

import com.ogy.weixin.pojo.*;
import com.ogy.weixin.service.impl.WeixinCoreServiceImpl;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class TestWx {



    @Test
    public void testMsg(){
        Map<String,String> reqestMap = new HashMap<>();
        reqestMap.put("ToUserName","to");
        reqestMap.put("FromUserName","from");
        reqestMap.put("MsgType","type");
        TextMessage tm = new TextMessage(reqestMap,"地方");
        System.out.println(tm);

        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String s = xStream.toXML(tm);
        System.out.println(s);

    }

    @Test
    public void testToken(){

        System.out.println(WeixinCoreServiceImpl.getAccessToken());
        System.out.println(WeixinCoreServiceImpl.getAccessToken());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testButton(){
        Button button = new Button();
        button.getButton().add(new ClickButton("一级点击","1-1"));
        button.getButton().add(new ViewButton("一级点击","http://www.baidu.com"));
        SubButton subButton = new SubButton("有字菜单");
        subButton.getSub_button().add(new PhotoOrAlbumButton("传图","2-3-1"));
        subButton.getSub_button().add(new ClickButton("点击","2-3-2"));
        subButton.getSub_button().add(new ViewButton("去购物","http://www.taobao.com"));
        button.getButton().add(subButton);
        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());
    }
}
