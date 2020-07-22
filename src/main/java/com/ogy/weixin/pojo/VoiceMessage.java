package com.ogy.weixin.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{

    @XStreamAlias("MediaId")
    private String mediaId;

    public VoiceMessage(Map<String,String> reqestMap, String mediaId){
        super(reqestMap);
        this.setMsgType("voice");
        this.setMediaId(mediaId);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
