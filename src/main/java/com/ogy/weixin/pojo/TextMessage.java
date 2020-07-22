package com.ogy.weixin.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class TextMessage extends BaseMessage{

    @XStreamAlias("Content")
    private String content;

    public TextMessage(Map<String,String> reqestMap,String content){
        super(reqestMap);
        this.setMsgType("text");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "toUserName='" + this.getToUserName() + '\'' +
                ", fromUserName='" + this.getFromUserName() + '\'' +
                ", createTime='" + this.getCreateTime() + '\'' +
                ", msgType='" + this.getMsgType() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
