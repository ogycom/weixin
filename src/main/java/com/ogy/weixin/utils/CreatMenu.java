package com.ogy.weixin.utils;

import com.ogy.weixin.pojo.*;
import com.ogy.weixin.service.impl.WeixinCoreServiceImpl;
import net.sf.json.JSONObject;

public class CreatMenu {
    public static void main(String[] args) {
        creat();
//        delete();
    }

    public static void creat(){
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
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WeixinCoreServiceImpl.getAccessToken());
        String result = WeixinUtil.post(url, jsonObject.toString());
        System.out.println(result);
    }

    public static void delete(){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",WeixinCoreServiceImpl.getAccessToken());
        System.out.println(url);
        String s = WeixinUtil.get(url);
        System.out.println(s);
    }

}
