package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author linmaojun
 * 类名称: ImageMessage
 * 类描述:图片消息
 * 创建时间: 2019年03月12日  16:49
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{
    /**
     * 通过素材管理中的接口上传多媒体文件
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    public ImageMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("image");
        this.mediaId=mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
