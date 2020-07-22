package com.ogy.weixin.controller;

import com.ogy.weixin.service.WeixinCoreService;
import com.ogy.weixin.utils.WeixinMessageUtil;
import com.ogy.weixin.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private WeixinCoreService weixinCoreService;

//        参数	描述
//        signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//        timestamp	时间戳
//        nonce	随机数
//        echostr	随机字符串
    @GetMapping
    public String hello(HttpServletRequest request){
        System.out.println("hello");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        try {
            InputStream in = request.getInputStream();
            byte[] buf = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len=in.read(buf))!=-1){
                sb.append(new String(buf,0,len));
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(WeixinUtil.checkSignature(signature,timestamp,nonce)){
            logger.info("效验成功");
            return echostr;
        }else {
            return null;
        }
    }

    @PostMapping
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response){
        System.out.println("getWeiXinMessage()...");
        Map<String, String> parseXml = WeixinMessageUtil.parseXml(request);
        System.out.println("paresXml(map): "+parseXml);
        String respXml = weixinCoreService.getRespose(parseXml);
//        String respXml = "<xml>" +
//                "<ToUserName><![CDATA["+parseXml.get("FromUserName")+"]]></ToUserName>" +
//                "<FromUserName><![CDATA["+parseXml.get("ToUserName")+"]]></FromUserName>" +
//                "<CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime>" +
//                "<MsgType><![CDATA[text]]></MsgType>" +
//                "<Content><![CDATA["+"how old are you?"+"]]></Content>" +
//                "</xml>";
        System.out.println("respXml(xml): "+respXml);
        return respXml;
    }

}
