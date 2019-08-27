package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author linmaojun
 * 类名称: VoiceMessage
 * 类描述:语音消息
 * 创建时间: 2019年03月12日  16:51
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
    /**
     * 通过素材管理中的接口上传多媒体文件
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    public VoiceMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
