package com.ogy.weixin.pojo;

//        参数	描述
//        ToUserName	开发者微信号
//        FromUserName	发送方帐号（一个OpenID）
//        CreateTime	消息创建时间 （整型）
//        MsgType	消息类型，文本为text
//        Content	文本消息内容
//        MsgId	消息id，64位整型

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Map;

@XStreamAlias("xml")
public class BaseMessage {

    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage(Map<String,String> reqestMap){
        this.toUserName = reqestMap.get("FromUserName");
        this.fromUserName = reqestMap.get("ToUserName");
        this.createTime = System.currentTimeMillis()/1000+"";
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
