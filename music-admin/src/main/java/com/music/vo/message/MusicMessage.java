package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author linmaojun
 * 类名称: MusicMessage
 * 类描述:
 * 创建时间: 2019年03月12日  16:56
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage{
    /**
     * 音乐对象
     */
    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(Map<String, String> requestMap, Music music) {
        super(requestMap);
        this.setMsgType("music");
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
