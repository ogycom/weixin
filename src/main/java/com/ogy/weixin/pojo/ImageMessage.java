package com.ogy.weixin.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("MediaId")
    private String mediaId;

    public ImageMessage(Map<String,String> reqestMap,String mediaId){
        super(reqestMap);
        this.setMsgType("image");
        this.setMediaId(mediaId);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
