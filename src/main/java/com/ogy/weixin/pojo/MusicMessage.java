package com.ogy.weixin.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{

    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(Map<String,String> reqestMap, Music music){
        super(reqestMap);
        this.setMsgType("music");
        this.music=music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

}
